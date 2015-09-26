# regurgitator-core
regurgitator is a modular, extendable java processing engine designed for regurgitating canned or clever responses to incoming requests.

it provides a series of executable steps and constructs, that can be hand or file configured to do whatever you need it to do when a request needs to be processed.
you can also add your own steps, for whatever it doesn't do out of the box.

it can be configured using xml or json or extended to use any other document format.

it can work with http to mock/stub http services, or be embedded within any other request / response mechanism.

it is separated out into relevant components, so you only have to include the parts you need in your project; configure it to do what you want; deploy it and go.

