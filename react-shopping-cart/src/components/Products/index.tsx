import { useProducts } from 'contexts/products-context';
import Products from './Products';

const ProductsContainer = () => {
  const { products, error, isFetching } = useProducts();

  if (error) {
    return <div style={{ color: 'red', padding: '1em' }}>Error: {error}</div>;
  }

  if (!isFetching && (!products || products.length === 0)) {
    return <div style={{ padding: '1em' }}>No products found.</div>;
  }

  return <Products products={products} />;
};

export default ProductsContainer;
