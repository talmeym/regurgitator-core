# regurgitator-core

regurgitator is a lightweight, modular, extendable java framework that you configure to 'regurgitate' canned or clever responses to incoming requests; useful for quickly mocking or prototyping services without writing any code. simply configure, deploy and run.

start your reading here: [regurgitator-all](https://talmeym.github.io/regurgitator-all#regurgitator)

[``apidocs``](https://regurgitator.emarte.uk/apidocs/regurgitator-core/0.1.2/)

## messages

upon invocation, regurgitator models each incoming request as a ``message``, passed to regurgitator for processing. this message holds all data accessible by the steps configured to run, and is passed to each step as it is executed. depending on its type, a step might read from, add to, or aggregate together data from within the message. when the processing required is complete (and if configured to do so) a step will return a response (or responses) to the incoming request, effectively ending the invocation - job done.

**accept request (as message)** => **execute steps (build responses using message data)** => **send back response(s)**

a message may be pre-populated with input data before regurgitator is invoked, such as metadata about an http call. each data item is stored in the message as a ``parameter``, stored under a ``context``, which groups a set of related parameters together. the default context is simply ``parameters``. some more specific contexts (for [http](https://talmeym.github.io/regurgitator-extensions-web#regurgitator-over-http)) include ``request-headers``, ``response-payload`` and ``global-metadata``. the message also provides the response callback mechanism through which responses are sent back out of regurgitator. see code example [here](https://talmeym.github.io/regurgitator-all#example-java-code).

a message can also be auto-populated with data that persists between requests, through the use of [``global data``](https://talmeym.github.io/regurgitator-extensions-web#global-metadata-servlet) and [``sessions``](https://talmeym.github.io/regurgitator-core-xml#identify-session).

## steps

regurgitator-core provides the following basic steps:
- ``sequence`` ([xml](https://talmeym.github.io/regurgitator-core-xml#sequence), [json](https://talmeym.github.io/regurgitator-core-json#sequence), [yml](https://talmeym.github.io/regurgitator-core-yml#sequence)) a collection of steps, run one after another
- ``decision`` ([xml](https://talmeym.github.io/regurgitator-core-xml#decision), [json](https://talmeym.github.io/regurgitator-core-json#decision), [yml](https://talmeym.github.io/regurgitator-core-yml#decision)) a collection of steps where ``rules`` and ``conditions`` dictate which steps to run
- ``create-parameter`` ([xml](https://talmeym.github.io/regurgitator-core-xml#create-parameter), [json](https://talmeym.github.io/regurgitator-core-json#create-parameter), [yml](https://talmeym.github.io/regurgitator-core-yml#create-parameter)) store a data item as a parameter within the message, with a name and a type
- ``build-parameter`` ([xml](https://talmeym.github.io/regurgitator-core-xml#build-parameter), [json](https://talmeym.github.io/regurgitator-core-json#build-parameter), [yml](https://talmeym.github.io/regurgitator-core-yml#build-parameter)) build a parameter using a ``value builder``, aggregating many parameter values
- ``generate-parameter`` ([xml](https://talmeym.github.io/regurgitator-core-xml#generate-parameter), [json](https://talmeym.github.io/regurgitator-core-json#generate-parameter), [yml](https://talmeym.github.io/regurgitator-core-yml#generate-parameter)) create a parameter value from scratch, using a ``value generator``
- ``create-response`` ([xml](https://talmeym.github.io/regurgitator-core-xml#create-response), [json](https://talmeym.github.io/regurgitator-core-json#create-response), [yml](https://talmeym.github.io/regurgitator-core-yml#create-response)) return a response from regurgitator; either a static value or from a parameter
- ``identify-session`` ([xml](https://talmeym.github.io/regurgitator-core-xml#identify-session), [json](https://talmeym.github.io/regurgitator-core-json#identify-session), [yml](https://talmeym.github.io/regurgitator-core-yml#identify-session)) use the value of a parameter to identify / look up a session object, to hold data between requests
- ``record-message`` ([xml](https://talmeym.github.io/regurgitator-core-xml#record-message), [json](https://talmeym.github.io/regurgitator-core-json#record-message), [yml](https://talmeym.github.io/regurgitator-core-yml#record-message)) output the contents of a ``message``, at any point during processing, to a file or standard output

more steps are available in other modules ([ext](https://talmeym.github.io/regurgitator-extensions#steps), [web](https://talmeym.github.io/regurgitator-extensions-web#steps), [mq](https://talmeym.github.io/regurgitator-extensions-mq#steps))

## constructs

regurgitator uses the following set of constructs / concepts to provide it's processing:
- ``parameter type`` each parameter has a type, which dictates how it is represented, as well as how it can be merged with another parameter. provided types include ``STRING`` ``NUMBER`` and ``DECIMAL`` along with list and set types
- ``value builder`` aggregates many parameter values into one. provided builders include support for popular templating engines
- ``value generator`` create a value from scratch, such as a random number or UUID
- ``value processor`` all steps that involve parameters can have extra processing wired in, to alter their initial value after it has been created, built or generated
- ``rules behaviour`` while rules determine how ``decision`` steps choose which child step to execute, ``rules behaviour`` govern what to do if more than one rule passes
- ``condition behaviour`` all conditions for a rule must be met for it to pass. each condition evaluates a parameter; its behaviour governs the kind of evaluation performed

just as custom steps can be added to extend regurgitator to meet your needs, you can also provide your own construct implementations to further extend the capabilities of the framework. 

## core constructs

regurgitator-core provides the following basic constructs:

#### parameter types
- ``STRING`` based on the ``java.lang.String`` java type
- ``NUMBER`` based on the ``java.lang.Long`` java type
- ``DECIMAL`` based on the ``java.lang.Double`` java type

all of the above have list and set types, holding collections of the java type, e.g. ``LIST_OF_STRING``, ``SET_OF_DECIMAL``

#### value generators
- ``number-generator`` ([xml](https://talmeym.github.io/regurgitator-core-xml#number-generator), [json](https://talmeym.github.io/regurgitator-core-json#number-generator), [yml](https://talmeym.github.io/regurgitator-core-yml#number-generator)) generates a random number parameter value
- ``uuid-generator`` ([xml](https://talmeym.github.io/regurgitator-core-xml#uuid-generator), [json](https://talmeym.github.io/regurgitator-core-json#uuid-generator), [yml](https://talmeym.github.io/regurgitator-core-yml#uuid-generator)) generates a unique UUID parameter value

#### value processors
- ``extract-processor`` ([xml](https://talmeym.github.io/regurgitator-core-xml#extract-processor), [json](https://talmeym.github.io/regurgitator-core-json#extract-processor), [yml](https://talmeym.github.io/regurgitator-core-yml#extract-processor)) extract one value from another, using java's java.text.MessageFormat syntax
- ``substitute-processor`` ([xml](https://talmeym.github.io/regurgitator-core-xml#substitute-processor), [json](https://talmeym.github.io/regurgitator-core-json#substitute-processor), [yml](https://talmeym.github.io/regurgitator-core-yml#substitute-processor)) processes a string value, replacing instances of one string with another
- ``index-processor`` ([xml](https://talmeym.github.io/regurgitator-core-xml#index-processor), [json](https://talmeym.github.io/regurgitator-core-json#index-processor), [yml](https://talmeym.github.io/regurgitator-core-yml#index-processor)) processes a collection, returning the data item at a given index
- ``index-of-processor`` ([xml](https://talmeym.github.io/regurgitator-core-xml#index-of-processor), [json](https://talmeym.github.io/regurgitator-core-json#index-of-processor), [yml](https://talmeym.github.io/regurgitator-core-yml#index-of-processor)) processes a collection, returning the index of a given data value
- ``size-processor`` ([xml](https://talmeym.github.io/regurgitator-core-xml#size-processor), [json](https://talmeym.github.io/regurgitator-core-json#size-processor), [yml](https://talmeym.github.io/regurgitator-core-yml#size-processor)) processes a collection, returning its size

more constructs are available in other modules ([ext](https://talmeym.github.io/regurgitator-extensions#constructs), [web](https://talmeym.github.io/regurgitator-extensions-web#constructs))
