# DataPoint API Service

A prototype code written with the [Spring Boot 2 framework](https://projects.spring.io/spring-boot/) to retrieve DataPoint records. The `StreamLambdaHandler` object is the main entry point for Lambda.

The application can be deployed in an AWS account using the [Serverless Application Model](https://github.com/awslabs/serverless-application-model). The `template.yml` file in the root folder contains the application definition.

## Pre-requisites

* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Gradle](https://gradle.org/) or [Maven](https://maven.apache.org/)

## Build

```bash
mvn clean package
```

## Deployment

### Local

We can use [AWS SAM Local](https://github.com/awslabs/aws-sam-local) to start this project.

First, install SAM local:

```bash
$ npm install -g aws-sam-local
```

Next, from the project root folder - where the `template.yaml` file is located - start the API with the SAM Local CLI.

```bash
$ sam local start-api --warm-containers EAGER --template template.yaml
```

Please note, the option `--warm-containers EAGER` is used to prevent the container that runs the function from starting every time it is invoked.  Using a new shell, we can send DataPoint requests to the API:

```bash
$ curl -s http://127.0.0.1:3000/dps | python -m json.tool
```

and

```bash
$ curl -s http://127.0.0.1:3000/dps/3 | python -m json.tool
```

### AWS

#### Deploy with AWS CLI

We can use the [AWS CLI](https://aws.amazon.com/cli/) to quickly deploy this application to AWS Lambda and Amazon API Gateway with the SAM template.

An S3 bucket is required to store the artifacts for deployment. Once the S3 bucket has provisioned, run the following command from the project's root folder - where the `template.yaml` file is located:

```bash
$ aws cloudformation package --template-file template.yaml --output-template-file output-template.yaml --s3-bucket <THE S3 BUCKET NAME>
```

We can now use the cli to deploy the application. Choose a stack name and run the `aws cloudformation deploy` command from the output of the package command.
 
```bash
$ aws cloudformation deploy --template-file output-template.yaml --stack-name ServerlessDataPointApi --capabilities CAPABILITY_IAM
```

Once the application is deployed, we can describe the stack to show the API endpoint that was created. The endpoint should be the `ServerlessDataPointApi` key of the `Outputs` property:

```bash
$ aws cloudformation describe-stacks --stack-name ServerlessDataPointApi
```

Copy down the endpoint URL from `OutputValue` of the above output and we can now use it to test the endpoint,

```bash
$ curl -s https://xxxxxxx.execute-api.us-east-1.amazonaws.com/dps | python -m json.tool
```

#### Deploy with SAM Tool

In a shell, navigate to the sample's folder and use the SAM CLI to build a deployable package

```bash
$ sam build
```

This command compiles the application and prepares a deployment package in the `.aws-sam` sub-directory.

To deploy the application in an AWS account, we can use the SAM CLI's guided deployment process and follow the instructions on the screen

```bash
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. We can use `curl` or a web browser to make a call to the URL

```bash
$ curl https://xxxxxxxxxx.execute-api.us-west-2.amazonaws.com/dps
```