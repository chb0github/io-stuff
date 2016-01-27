# io-stuff
Not much here at the moment. Usefull

* **ScanningFileFilter** 
Reads a file in and does a binary scan of the file; shifting bytes left until a pattern is matched. Upon matching your scan criteria your consumer
is invoked and the stream is passed in so you can read from it in your consumer. It's like a primitive grep. Hmm, maybe I could implement grep

* **ScanningInputStream**
A decorator on an input stream that will scan your stream looking to match your predicate

* **ScanningPredicate**
Scans a stream looking for a pattern. Tie this with inputstream, with FileFilter

* **CompositeOutputStream**
Will replicate all data to the constructed collections of output streams. Does not account for failure. Is not multithreaded (Maybe it should be?)

* **CapturingOutputStream**
Records data as it is written to the underlying output stream. Useful for monitoring. Kinda like a wiretap pattern

