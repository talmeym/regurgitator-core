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
- ``identify-session`` use the value of an parameter to identify / look up a session object, to hold information between requests
- ``create-response`` return a response from regurgitator; either a static value or from a parameter

regurgitator-core provides the basics of regurgitator; usable with minimal dependencies. other steps and constructs that do have dependencies are provided in [regurgitator-extensions](https://github.com/talmeym/regurgitator-extensions).

constructs
----------

regurgitator uses the following set of constructs / concepts to provide it's processing:
- ``parameter type`` each parameter has a type, which dictates how it is represented, as well as how it can be merged with another parameter. provided types include ``STRING`` ``NUMBER`` and ``DECIMAL`` along with list and set types
- ``value processor`` steps that involve parameters can have extra processing applied after their initial value has been created, built or generated

modules
-------

- regurgitator-core - the core set of steps and contructs that allow regurgitator to accept a request, process it and product a response (or many).
- regurgitator-xml,
- regurgitator-json - configure what regurgitator will do for you using a document file
