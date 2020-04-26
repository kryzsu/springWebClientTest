# stringWebClientTest
demo application for webclient usage in service and easy mock in test. (not complete)
It is a simple proxy application.
valid request:

GET http://localhost:8080/is-java with request body

return value for the next request body:
{"repo":"tetris"}
is
{"java": false}


return value for the next request body:
{"repo":"java"}
is
{"java": true}

