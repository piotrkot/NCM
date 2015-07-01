## How to build

  `$ mvn clean package -Pqulice`

Special quality policy is applied as a static code analysis. See:
http://www.qulice.com/ for details.

## How to run

  `$ java -jar target/ncm-task-1.0-SNAPSHOT.jar [-c] [-x] < input`

When running inappropriately help message is displayed.

## Technical decisions

* As the assignment mentions 'huge input', it was guessed the application
should rather deal with streams instead of strings.
* As the application reads from stdin, writes to stdout, and have
configuration options, it was guessed adding CLI is a good fit.
Here I chose [Apache Commons CLI](https://commons.apache.org/proper/commons-cli/).
* Logging is realized with Slf4j SimpleLogger. By default log
messages are sent to the console (`System.err`). It can be altered
with `org.slf4j.simpleLogger.logFile` system property.

## Questions

* "Model classes must be able to be used as keys in data structures." - 
Model classes? Is it expected to have MVC or MVP? I thought the design was
up to me, wasn't it? :)
Regarding classes as keys, this can be achieved by simply marking classes
`@EqualsAndHashCode` of lombok but I don't clearly see the reason to do so.

* "Consider huge input when dealing IO operations (entire dataset cannot
fit in heap)." - 
Unfortunately, there are no rules set on text, i.e.:
  * when there is only one dot at the end of the text,
all words must fit into memory to get them sorted. For huge inputs this
already might not be possible.
  * when there is no whitespace, one huge word must fit into memory to be
put into output stream. Again, may not be possible.

* "the words have to be sorted" - 
Although there is an example for sorting, it is not explicitly defined.
For that a collator is used with default locale. When one wants to change
sorting according to different locale it is required to provide it with
`-Duser.language` system property.

* "broken into sentences" - 
It is not explicitly defined how sentence split should be performed. Thus,
it was assumed
[Stanford Core NLP](http://nlp.stanford.edu/software/corenlp.shtml) tools
should suffice.

* "wiring" - 
Personally (as suggested with "Feel free" phrase), I did not find useful
to have wiring in this application. Having Dependency Injection
via constructors seems to me sufficient, simple and more testable.
See controversial but true:
http://www.yegor256.com/2014/10/03/di-containers-are-evil.html
