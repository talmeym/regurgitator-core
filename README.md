regurgitator
============
regurgitator is a modular, light-weight, extendable java-based processing engine designed to regurgitate canned or clever responses to incoming requests.

it provides a series of executable steps and constructs, that can be combined and configured to apply the simple or complex processing you require when a request is received.
you can also add your own steps, for whatever it doesn't do out of the box. 

it can be configured using xml or json files or extended to use any other document format.

it can work with http to mock/stub http services, or be embedded within any other request / response mechanism.

it is separated out into modules, so you only have to include the parts you need into your project, then configure it to do what you want, deploy it and go!

steps
-----

regurgitator-core provides the following basic steps:
- ``sequence`` a collection of steps, run one after another
- ``decision`` a collection of steps where ``rules`` and ``conditions`` dictate which steps are run
- ``create-parameter`` store a piece of information, with a name and a type, to be used in a response
- ``build-paramerer`` build a parameter using a ``value builder``, incorporating other parameters
- ``generate-parameter`` create a parameter from scratch, using a ``value generator``
- ``create-response`` return a response from regurgitator; either a static value or from a parameter
- ``identify-session`` use the value of a parameter to identify / look up a session object, to hold information between requests

regurgitator-core provides the basics of regurgitator; usable with minimal dependencies. other steps and constructs that do have dependencies are provided in [regurgitator-extensions](https://github.com/talmeym/regurgitator-extensions).

constructs
----------

regurgitator uses the following set of constructs / concepts to provide it's processing:
- ``parameter type`` each parameter has a type, which dictates how it is represented, as well as how it can be merged with another parameter. provided types include ``STRING`` ``NUMBER`` and ``DECIMAL`` along with list and set types
- ``value builder`` aggregates the values of many parameters into one. provided builders include support for popular templating engines. 
- ``value processor`` all steps that involve parameters can have extra processing wired in, to alter their initial value after it has been created, built or generated
- ``rule-behaviour`` rules determine how ``decision`` steps choose which child step to execute; their behaviour govern what to do if more than rule passes
- ``condition-behaviour`` all conditions for a rule must be met for it to pass. each condition evaluates the value of a parameter; it's behaviour governs the kind of evaluation performed

decision
  |->  step 1
  |->  step 2
  |->  rule-behaviour
  |->  rule 1
        |-> condition 1
        |-> condition 2
  |->  rule 2
        |-> condition 3
        |-> condition 4

modules
-------

- regurgitator-core - the core set of steps and contructs that allow regurgitator to accept a request, process it and product a response (or many).
- regurgitator-xml,
- regurgitator-json - configure what regurgitator will do for you using a document file
