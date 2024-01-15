

# Task Manager Docker Image 


Welcome to the Task Manager Docker Image repository. This Docker image allows you to easily deploy and run the Task Manager application.

## Getting Started

These instructions will cover usage information for the docker container.

### Prerequisites

In order to run this container you'll need docker installed.

- [Get Docker](https://docs.docker.com/get-docker/)

### Pulling the Docker Image

Before running the container, you need to pull the image from GitHub Packages:

```bash
docker pull ghcr.io/adamwaa/taskmanager:latest
```
This command will download the Task Manager Docker image to your local machine. Usage Container Parameters

Here are some basic commands to help you get started with using this Docker image.

To run the container in detached mode:

```bash


docker run -d ghcr.io/adamwaa/taskmanager:latest
```

To run the container with port forwarding and a specific name:

```bash

docker run -d -p 8080:8080 --name MyTaskManager taskmanager
```

This command will start the Task Manager and expose it on port 8080.

![2024-01-14_21h02_50-ezgif com-video-to-gif-converter](https://github.com/Adamwaa/taskmanager/assets/97319080/ff39949c-57f2-40a3-97a5-34c82bb7aae1)

# Testing with Postman

You can test the Task Manager application using Postman, a popular tool for testing APIs. Here’s how you can test the available endpoints:

#### 1. Sending a POST Request

To create a new task, you can send a POST request to `http://localhost:8080/tasks`. The request should include a JSON body as follows:

```json
{
  "pattern": "CFG",
  "input": "ABCDEFG"
}
```
This request will create a new task with the given pattern and input. The response from this request will include an ID for the newly created task.

#### 2. Sending a GET Request

Once you have the ID of a task, you can retrieve its details using a GET request to http://localhost:8080/tasks/{id}, where {id} is the ID of the task.

For example, if the ID of your task is c2377fae-d745-40ff-8393-1171fb7ccbee, you would send a GET request to 
```bash 
http://localhost:8080/tasks/c2377fae-d745-40ff-8393-1171fb7ccbee
```

This request will return the details of the task associated with the given ID.
![obraz](https://github.com/Adamwaa/taskmanager/assets/97319080/c591bc89-4cf6-4564-b6fd-952b19470356)



