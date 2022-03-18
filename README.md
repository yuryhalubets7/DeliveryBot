# DeliveryBot

## Description

DeliveryBot app allows you to put delivery info and get smallest path for it.
You can enter a grid size and a list of points representing houses in need of delivery. 
As a result you will see a list of instructions for getting DeliveryBot to those locations and delivering.

## Usage

To use DeliveryBot app, you need to clone repository and launch the app. Here you will see input field
where you can put delivery info and find smallest path. Result will be shown below button and 
will be empty if there is no any points.
In case of invalid entered data you will see detailed message about what is wrong.

## Examples

For the following input: `5x5 (1, 3) (4, 4)` correct result will be: `ENNNDEEEND`.
For more complex input like that: `5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)`
correct result will be: `DNDENNDEDESDEDDSDNNND`
