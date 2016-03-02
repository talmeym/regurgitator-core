regurgitator-core
=================

regurgitator is a modular, light-weight, extendable java-based processing framework designed to 'regurgitate' canned or clever responses to incoming requests.

See more at [regurgitator-all](http://github.com/talmeym/regurgitator-all)

### messages

upon invocation, regurgitator models the incoming request as a ``message``. this message holds all data accessible by the steps configured to run, and is passed to each step upon executed. depending on its type, each step might read from, add to, or aggregate together data from within the message. a message may be pre-populated with input data before regurgitator is invoked, such as metadata about an http call. each data item is stored in the message as a ``parameter``, stored under a ``context``, which connects a group of related parameters together. the default context is simply 'parameters'. some more specific contexts (for [http](http://github.com/talmeym/regurgitator-extensions-web)) include 'request-headers', 'response-payload' and 'global-metadata'. the message also provides a ``response-callback`` through which responses can be given.

### steps

regurgitator-core provides the following basic steps:
- ``sequence`` ([xml](https://github.com/talmeym/regurgitator-core-xml#sequence), [json](https://github.com/talmeym/regurgitator-core-json#sequence)) a collection of steps, run one after another
- ``decision`` ([xml](https://github.com/talmeym/regurgitator-core-xml#decision), [json](https://github.com/talmeym/regurgitator-core-json#decision)) a collection of steps where ``rules`` and ``conditions`` dictate which steps are run
- ``create-parameter`` store a piece of information, with a name and a type, to be used in a response
- ``build-paramerer`` build a parameter using a ``value builder``, incorporating other parameters
- ``generate-parameter`` create a parameter from scratch, using a ``value generator``
- ``create-response`` return a response from regurgitator; either a static value or from a parameter
- ``identify-session`` use the value of a parameter to identify / look up a session object, to hold information between requests

regurgitator-core provides the basics of regurgitator; usable with minimal dependencies. other steps and constructs that do have dependencies are provided in [regurgitator-extensions](https://github.com/talmeym/regurgitator-extensions).

### constructs

regurgitator uses the following set of constructs / concepts to provide it's processing:
- ``parameter type`` each parameter has a type, which dictates how it is represented, as well as how it can be merged with another parameter. provided types include ``STRING`` ``NUMBER`` and ``DECIMAL`` along with list and set types.
- ``value builder`` aggregates the values of many parameters into one. provided builders include support for popular templating engines. 
- ``value generator`` create parameter values from scratch, such as random numbers.
- ``value processor`` all steps that involve parameters can have extra processing wired in, to alter their initial value after it has been created, built or generated.
- ``rules behaviour`` while rules determine how ``decision`` steps choose which child step to execute, ``rules behaviour`` govern what to do if more than rule passes.
- ``condition behaviour`` all conditions for a rule must be met for it to pass. each condition evaluates a parameter; its behaviour governs the kind of evaluation performed.

just as custom steps can be added to extend regurgitator to meet your needs, you can also provide your own construct implementations to further extend the capabilities of the framework. 
