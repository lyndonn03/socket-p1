## About the Project

I made this small project to show the `Thread per Connection` model in its simplest form using `java.net` API. This is also my initial step on relearning how Java deals with network and concurrency stuffs. Note that this is just a model of a client-server communication that uses TCP instead of HTTP, but the underlying concepts are pretty much similar (not the same though) for this particular `Thread per Connection` model.

<br>

## Running the Project

The repository has two parts, the server and the client. These are two different applications, so to run these, you need to run the two applications individually. You must first run the server and run the client. You can use your favorite IDE or text editor to run these easily. Otherwise, you need to compile and run these applications using `Java CLI`.

<br>

## About the Thread per Connection Model

The thread per connection model, as its name suggests, is a model for client-server connection that allocates one thread per connection. This is the simplest way of network communication. When the client connects, you open a thread and that thread will be the server side end of the communication. The thread will never be destroyed unless the client or the server disconnects. 