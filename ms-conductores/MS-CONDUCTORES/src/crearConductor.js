const AWS = require('aws-sdk');
const dynamodb = new AWS.DynamoDB.DocumentClient();

exports.crearConductor = async (event) => {
    //we receive the attributes from the user in such a way that they are only the specific attributes we need.
    const { nombreProducto, descripcion, precio, stock } = JSON.parse(event.body);
    // Validations
    const validationError = validateProductData(nombreProducto, descripcion, precio, stock);
    if (validationError) {
      return {
        statusCode: 400,
        body: JSON.stringify({ error: validationError })
      };
    }
    //records that we will send to our table.
    const id = v4();
    const newItem = {
      id,
      nombreProducto,
      descripcion,
      precio,
      stock
    };
    const params = {
      TableName: 'productTable',
      Item: newItem
    };
    //send the data
    try {
          await dynamodb.put(params).promise();
          return {
              statusCode: 200,
              body: JSON.stringify(newItem)
          };
      } catch (error) {
          return {
              statusCode: 500,
              body: JSON.stringify({ error: 'Could not create product' })
          };
      }
  };