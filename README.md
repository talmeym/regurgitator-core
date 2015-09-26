# regurgitator
regurgitator is a modular, extendable java processing engine designed for regurgitating canned or clever responses to incoming requests.

it provides a series of executable steps and constructs, that can be hand or file configured to do whatever you need it to do when a request needs to be processed.
you can also add your own steps, for whatever it doesn't do out of the box.

it can be configured using xml or json or extended to use any other document format.

it can work with http to mock/stub http services, or be embedded within any other request / response mechanism.

it is separated out into modules, so you only have to include the parts you need into your project, then configure it to do what you want, deploy it and go.

#constructs

steps

core steps included in this module are:
- sequence
- decision

#modules

regurgitator-core - the core set of steps and contructs that allow regurgitator to accept a request, process it and product a response (or many).

regurgitator-xml, regurgitator-json - configure what regurgitator can do for you using an extendable document format