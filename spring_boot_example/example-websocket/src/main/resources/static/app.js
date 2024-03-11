const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8084/socket/v1/ws_app'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
    disconnect();
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function enterRoom() {
    console.log("enterRoom");
    var response = stompClient.subscribe('/chatting/' + $("#room").val(), function (message) {
        console.log('Received: ' + message);
        showMessage(JSON.parse(message.body).message);
    });

    var responseUser = stompClient.subscribe('/user/chatting', function (message) {
        console.log('Received: ' + message);
        showMessage(JSON.parse(message.body).message);
    });
    console.log('Subscribe response: %o', JSON.stringify(response));
    console.log('Subscribe response user: %o', JSON.stringify(responseUser));
}

function sendMessage() {
    console.log("sendMessage");
    let roomId = $("#room").val();
    let message = {
        roomId: roomId,
        message: $("#message").val()
    };
    stompClient.publish({
        destination: "/chatting/" + roomId,
        body: JSON.stringify(message)
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#enter" ).click(() => enterRoom());
    $( "#send" ).click(() => sendMessage());
});