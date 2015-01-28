JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java


CLASSES = server/Block.java \
	server/InitServer.java \
	server/PuzzleSolverServer.java \
	server/PuzzleThread.java \
	client/PuzzleSolverClient.java \
	client/IOHelper.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) client/*.class
	$(RM) server/*.class

startServer:
	rmiregistry &
	sleep 1
	xterm -e -hold "java PuzzleSolverServer" &


startClient:
	sleep 1
	xterm -e -hold "java PuzzleSolverClient" &

stop:
	killall -q rmiregistry &
	sleep 1
	killall -q xterm &
