import axios, { AxiosError } from 'axios';
import { IGetProductsResponse } from 'models';

const isProduction = process.env.NODE_ENV === 'production';

const MAX_RETRIES = 3;
const RETRY_DELAY_MS = 1000;

export const getProducts = async () => {
  let attempt = 0;
  let lastError: any = null;

  while (attempt < MAX_RETRIES) {
    try {
      let response: IGetProductsResponse;

      if (isProduction) {
        response = await axios.get(
          'https://react-shopping-cart-67954.firebaseio.com/products.json',
          { validateStatus: (status) => status >= 200 && status < 300 }
        );
        // HTTP status code validation is handled by axios unless validateStatus is overridden
        if (!response || !response.data) {
          throw new Error('No data received from server.');
        }
      } else {
        // Simulate async for local require
        response = await new Promise((resolve) => {
          setTimeout(() => {
            resolve(require('static/json/products.json'));
          }, 100);
        });
      }

      // Defensive: check for products array
      const products = response.data?.products;
      if (!Array.isArray(products)) {
        throw new Error('Malformed product data received.');
      }
      return products;
    } catch (error) {
      lastError = error;
      // Network error or HTTP error
      if (axios.isAxiosError(error)) {
        if (error.response) {
          // Server responded with a status outside 2xx
          console.error(`API Error: ${error.response.status} - ${error.response.statusText}`);
        } else if (error.request) {
          // No response received
          console.error('Network error: No response received from server.');
        } else {
          // Something else happened
          console.error('Error in setting up the request:', error.message);
        }
      } else {
        // Non-Axios error
        console.error('Unexpected error:', error);
      }

      attempt++;
      if (attempt < MAX_RETRIES) {
        // Exponential backoff could be used here
        await new Promise((resolve) => setTimeout(resolve, RETRY_DELAY_MS * attempt));
      }
    }
  }

  // After retries, throw a user-friendly error
  throw new Error(
    'Unable to load products at this time. Please check your internet connection or try again later.'
  );
};
