import { useCallback, useRef } from 'react';

import { useProductsContext } from './ProductsContextProvider';
import { IProduct } from 'models';
import { getProducts } from 'services/products';

const useProducts = () => {
  const {
    isFetching,
    setIsFetching,
    products,
    setProducts,
    filters,
    setFilters,
    error,
    setError,
  } = useProductsContext();

  // Cache all products in a ref to avoid re-fetching
  const allProductsRef = useRef<IProduct[] | null>(null);

  const fetchProducts = useCallback(async () => {
    setIsFetching(true);
    setError(null);
    try {
      const products = await getProducts();
      setIsFetching(false);
      setProducts(products);
      allProductsRef.current = products;
    } catch (err: any) {
      setIsFetching(false);
      setError(err.message || 'Failed to load products.');
    }
  }, [setIsFetching, setProducts, setError]);

  const filterProducts = useCallback((filters: string[]) => {
    setIsFetching(true);
    setError(null);
    try {
      let filteredProducts;
      console.time('filterProducts');
      const allProducts = allProductsRef.current;
      if (!allProducts) {
        setIsFetching(false);
        setError('No products loaded to filter.');
        return;
      }
      if (filters && filters.length > 0) {
        const filterSet = new Set(filters);
        filteredProducts = allProducts.filter((p: IProduct) =>
          p.availableSizes.some((size: string) => filterSet.has(size))
        );
      } else {
        filteredProducts = allProducts;
      }
      console.timeEnd('filterProducts');
      setFilters(filters);
      setProducts(filteredProducts);
      setIsFetching(false);
    } catch (err: any) {
      setIsFetching(false);
      setError(err.message || 'Failed to filter products.');
    }
  }, [setIsFetching, setFilters, setProducts, setError]);

  return {
    isFetching,
    fetchProducts,
    products,
    filterProducts,
    filters,
    error,
  };
};

export default useProducts;
