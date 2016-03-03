# regurgitator-core

regurgitator is a modular, light-weight, extendable java-based processing framework designed to 'regurgitate' canned or clever responses to incoming requests; useful for mocking or prototyping services.

start your reading here: [regurgitator-all](http://github.com/talmeym/regurgitator-all#regurgitator)

## messages

upon invocation, regurgitator models each incoming request as a ``message``, passed to regurgitator for processing. this message holds all data accessible by the steps configured to run, and is passed to each step as it is executed. depending on its type, a step might read from, add to, or aggregate together data from within the message. when the processing required is complete (and if configured to do so) a step will return back a response (or responses) to the incoming request, effectively ending the invocation - job done.

**accept request (message)** => **execute steps (manipulate data)** => **send back response(s)**

a message may be pre-populated with input data before regurgitator is invoked, such as metadata about an http call. each data item is stored in the message as a ``parameter``, stored under a ``context``, which groups a set of related parameters together. the default context is simply 'parameters'. some more specific contexts (for [http](http://github.com/talmeym/regurgitator-extensions-web)) include 'request-headers', 'response-payload' and 'global-metadata'. the message also provides the ``response-callback`` through which responses are sent. see code example [here] (https://github.com/talmeym/regurgitator-all#example-code)

## steps

regurgitator-core provides the following basic steps:
- ``sequence`` ([xml](https://github.com/talmeym/regurgitator-core-xml#sequence), [json](https://github.com/talmeym/regurgitator-core-json#sequence)) a collection of steps, run one after another
- ``decision`` ([xml](https://github.com/talmeym/regurgitator-core-xml#decision), [json](https://github.com/talmeym/regurgitator-core-json#decision)) a collection of steps where ``rules`` and ``conditions`` dictate which steps to run
- ``create-parameter`` store a data item as a parameter within the message, with a name and a type
- ``build-parameter`` build a parameter using a ``value builder``, aggregating many parameter values
- ``generate-parameter`` create a parameter value from scratch, using a ``value generator``
- ``create-response`` return a response from regurgitator; either a static value or from a parameter
- ``identify-session`` use the value of a parameter to identify / look up a session object, to hold data between requests

regurgitator-core provides the basics of regurgitator; usable with minimal dependencies. other steps and constructs that do have dependencies are provided in [regurgitator-extensions](https://github.com/talmeym/regurgitator-extensions).

## constructs

regurgitator uses the following set of constructs / concepts to provide it's processing:
- ``parameter type`` each parameter has a type, which dictates how it is represented, as well as how it can be merged with another parameter. provided types include ``STRING`` ``NUMBER`` and ``DECIMAL`` along with list and set types
- ``value builder`` aggregates many parameter values into one. provided builders include support for popular templating engines
- ``value generator`` create a values from scratch, such as a random number or UUID
- ``value processor`` all steps that involve parameters can have extra processing wired in, to alter their initial value after it has been created, built or generated
- ``rules behaviour`` while rules determine how ``decision`` steps choose which child step to execute, ``rules behaviour`` govern what to do if more than rule passes
- ``condition behaviour`` all conditions for a rule must be met for it to pass. each condition evaluates a parameter; its behaviour governs the kind of evaluation performed

just as custom steps can be added to extend regurgitator to meet your needs, you can also provide your own construct implementations to further extend the capabilities of the framework. 

## core constructs

regurgitator-core provides the following basic constructs:

#### parameter types
- ``STRING`` based on the java.lang.String java type
- ``NUMBER`` based on the java.lang.Long java type
- ``DECIMAL`` based on the java.lang.Double java type

all of the above have list and set types, holding collections of the java type, eg. ``LIST_OF_STRING``, ``SET_OF_DECIMAL``

#### value generators
- ``number-generator`` generates a random number parameter value
- ``uuid-generator`` generates a unique UUID parameter value

#### value processors
- ``extract-processor`` extract one value from another, using java's java.text.MessageFormat syntax
- ``substitute-processor`` processes a parameter value, replacing instances of one string with another
- ``size-processor`` processes a collection, returning its size
- ``index-processor`` processes a collection, returning the data item at a given index
- ``index-of-processor`` processes a collection, returning the index of a given data value

