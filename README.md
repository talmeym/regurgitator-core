regurgitator
============
regurgitator is a modular, extendable java processing engine designed for regurgitating canned or clever responses to incoming requests.

it provides a series of executable steps and constructs, that can be hand or file configured to do whatever you need it to do when a request needs to be processed.
you can also add your own steps, for whatever it doesn't do out of the box.

it can be configured using xml or json or extended to use any other document format.

it can work with http to mock/stub http services, or be embedded within any other request / response mechanism.

it is separated out into modules, so you only have to include the parts you need into your project, then configure it to do what you want, deploy it and go!

steps
-----

regurgitator-core provides the following basic steps:
- sequence - a collection of steps, run one after another
- decision - a collection of steps where ``rules`` and ``conditions`` dictate which steps are run
- create-parameter - store a piece of information, with a name and a type, to be used in a response
- build-paramerer - build a parameter using a ``value builder``, incorporating other parameters
- generate-parameter - create a parameter from scratch, using a ``value generator``
- identify-session - use the value of an parameter to identify / look up a session object, to hold information between requests

regurgittor-core provides the basics of regurgitator; usable with minimal dependencies. other steps and constructs that do have dependencies are provided in [regurgitator-extensions](https://github.com/talmeym/regurgitator-extensions).

constructs
----------

regurgitator uses the following set of constructs / concepts to do it's processing:
- ``ParameterType`` - each parameter has a type, which dictates how it is represented, as well as how it can be merged with another parameter


modules
-------

- regurgitator-core - the core set of steps and contructs that allow regurgitator to accept a request, process it and product a response (or many).
- regurgitator-xml,
- regurgitator-json - configure what regurgitator will do for you using a document file