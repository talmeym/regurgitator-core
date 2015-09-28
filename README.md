regurgitator
============
regurgitator is a modular, light-weight, extendable java-based processing engine designed to 'regurgitate' canned or clever responses to incoming requests.

it provides a series of executable ``steps`` and ``constructs``, that can be combined and configured to apply simple or complex processing as you require, when a request is received.
you can also add your own steps, for whatever it doesn't do out of the box. 

it can be configured using ``xml`` or ``json`` files or extended to use any other document format.

it can work with ``http`` to mock/stub http services, or be embedded within any other request / response mechanism.

it is separated out into modules, so you only have to include the parts you need into your project, then configure it to do what you want, deploy it and go!

steps
-----

regurgitator-core provides the following basic steps:
- ``sequence`` a collection of steps, run one after another.
- ``decision`` a collection of steps where ``rules`` and ``conditions`` dictate which steps are run.
- ``create-parameter`` store a piece of information, with a name and a type, to be used in a response.
- ``build-paramerer`` build a parameter using a ``value builder``, incorporating other parameters.
- ``generate-parameter`` create a parameter from scratch, using a ``value generator``.
- ``create-response`` return a response from regurgitator; either a static value or from a parameter.
- ``identify-session`` use the value of a parameter to identify / look up a session object, to hold information between requests.

regurgitator-core provides the basics of regurgitator; usable with minimal dependencies. other steps and constructs that do have dependencies are provided in [regurgitator-extensions](https://github.com/talmeym/regurgitator-extensions).

constructs
----------

regurgitator uses the following set of constructs / concepts to provide it's processing:
- ``parameter type`` each parameter has a type, which dictates how it is represented, as well as how it can be merged with another parameter. provided types include ``STRING`` ``NUMBER`` and ``DECIMAL`` along with list and set types
- ``value builder`` aggregates the values of many parameters into one. provided builders include support for popular templating engines. 
- ``value generator`` create parameter values from scratch, such as random numbers
- ``value processor`` all steps that involve parameters can have extra processing wired in, to alter their initial value after it has been created, built or generated.
- ``rule behaviour`` rules determine how ``decision`` steps choose which child step to execute; their behaviour govern what to do if more than rule passes.
- ``condition behaviour`` all conditions for a rule must be met for it to pass. each condition evaluates the value of a parameter; it's behaviour governs the kind of evaluation performed.

just as custom steps can be added to extend regurgitator to meet your needs, you can also provide your own construct implementations to further extend the capabilities of the framework. 

modules
-------

some important modules

- [regurgitator-core](https://github.com/talmeym/regurgitator-core) provides the core steps and contructs to accept a request, process it and produce responses.
- [regurgitator-core-xml](https://github.com/talmeym/regurgitator-core-xml) allows configuration using a namespaced, schema validated xml document.
- [regurgitator-core-json](https://github.com/talmeym/regurgitator-core-json) allows configuration using a json document.
- [regurgitator-extensions](https://github.com/talmeym/regurgitator-extensions) provides useful extension steps and construct implementations.
- [regurgitator-extensions-web](https://github.com/talmeym/regurgitator-extensions-web) provides support for http, including the regurgitator servlet.

messages
--------

an incoming request is modelled as a ``message``. regurgitator takes a message in for processing, which can be pre-populated with relevant parameters, and uses a ``response-callback`` within the message to pass back responses. each parameter is stored in the message under a ``context``, which is a group of related parameters. the default context is just 'parameters'. other system contexts (for http) include 'request-headers', 'response-payload', 'global-metadata'.
