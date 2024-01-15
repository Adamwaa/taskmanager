const apiBaseUrl = "http://localhost:8080/tasks";

function createTask() {
    const pattern = document.getElementById('pattern').value;
    const inputText = document.getElementById('inputText').value;

    fetch(apiBaseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ pattern: pattern, input: inputText }),
    })
    .then(response => response.json())
        .then(data => {
            console.log('Task created:', data);
            const createdTaskIdElement = document.getElementById('taskIdToCopy');
            createdTaskIdElement.textContent = data.id;
            document.getElementById('createdTaskId').style.display = 'block'; // Pokaż element z ID zadania
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }

function copyToClipboard() {
    const taskIdToCopy = document.getElementById('taskIdToCopy').textContent;
    navigator.clipboard.writeText(taskIdToCopy)
        .then(() => {
            alert('Task ID copied to clipboard!');
        })
        .catch(err => {
            console.error('Could not copy text: ', err);
        });
}

function getTaskDetails() {
    const taskId = document.getElementById('taskId').value;

    if (!taskId) {
        alert('Please enter a Task ID.');
        return;
    }

    const taskDetails = document.getElementById('taskDetails');
    taskDetails.textContent = 'Checking task status...';

    const interval = setInterval(() => {
        fetch(`${apiBaseUrl}/${taskId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Task not found');
            }
            return response.json();
        })
        .then(task => {
            taskDetails.textContent = `Result: position:${task.position}, typos:${task.typos}, Status: ${task.processingStatus}, Progress: ${task.completionProgress}%`;

            // Jeśli zadanie jest zakończone, zatrzymujemy interval
            if (task.processingStatus === 'COMPLETED' || task.processingStatus === 'ERROR') {
                clearInterval(interval);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            clearInterval(interval);
            alert(error.message);
        });
    }, 1000); // odświeżanie co sekundę
}
