# Java Tiered Compilation - Investigation

## Useful Links

- https://www.oracle.com/technical-resources/articles/java/architect-evans-pt1.html
- https://blog.joda.org/2011/08/printcompilation-jvm-flag.html
- https://github.com/AdoptOpenJDK/jitwatch
- https://gist.github.com/chrisvest/2932907 (explanation of compilation logs)
- https://theboreddev.com/analysing-jit-compilation-in-jvm/
- https://www.chrisnewland.com/jitwatch-code-cache-visualisation-407

## Some VM options - available prior to some of the later Java SE 7 releases
- -Xint - run all code in **interpreted** mode
- -Xcomp - compile all code - todo: how much it will be compiled? 


## HotSpot Virtual Machine
(https://skillsmatter.com/skillscasts/5243-chris-newland-hotspot-profiling-with-jit-watch)

- Two compilers
    - C1 - client compiler (usually good candidate for GUI apps - where quick startup and rock-solid optimization)
    - C2 - server compiler (usually good candidate for server apps, long-running)
    - Tiered compilation = C1 + C2
    - OSR - On Step Replacement - code running in a loop that was replaced
    
### VM options    
- Tiered compilation is default since Java SE 8
- the flags may also be placed in a flags file, .hotspotrc by default, or configurable as -XX:Flags=myhotspotrc.txt
- -XX:+UnlockDiagnosticVMOptions - is need for usage of *LogCompilation*
- -XX:+LogCompilation
    - -XX:LogFile=<path to file> - to specify output file
    -  -XX:+PrintCompilation - alternative for output to console, no need for *UnlockDiagnosticVMOptions*, provides less information than *LogCompilation*
- -XX:+TraceClassLoading (JITWatch)
- -XX:+PrintAssembly
    - https://wiki.openjdk.java.net/display/HotSpot/PrintAssembly
    - requires 'hsdis' binary in jre/lib/<arch>/server
    - significant performance overhead
    - https://metebalci.com/blog/how-to-build-the-hsdis-disassembler-plugin-on-ubuntu-18/
    
### Options during runtime
- JIT compilation
- Decompilation - when HotSpot makes mistake and runs bytecode again
- Inlining 
    - success/failure - reason
    - instead of calling of a method put method body to the caller
    - helps to gain a lot of performance
    - small methods are more likely to be inlined (< 325 B)
    - by default, Java HotSpot VM will try to inline methods that contain less than 35 bytes of JVM bytecode.
- Branch probabilities - Branch prediction (C2)
- Intrinsics - where VM decides what implementation is the best
- Code Cache
    - stores native code compiled byt HotSpot
    - if full some methods have to be removed