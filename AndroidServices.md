# Service

A service is a component that runs in the background, so, it has no user interface. It is useful to perform long-term operations such as logging GPS. A service will continue to run even if the application that started it exits.

A Service is an application component representing either an application's desire to perform a longer-running operation while not interacting with the user or to supply functionality for other applications to use.

- Each service class must have a corresponding <service> declaration in its package's AndroidManifest.xml
- Services can be started with Context.startService() and Context.bindService()
- Note that services, like other application objects, run in the main thread of their hosting process. This means that, if your service is going to do any CPU intensive (such as MP3 playback) or blocking (such as networking) operations, it should spawn its own thread in which to do that work. See [Processes and Threads](https://developer.android.com/guide/topics/fundamentals/processes-and-threads) and [JobIntentService class](https://developer.android.com/reference/androidx/core/app/JobIntentService.html)
- 