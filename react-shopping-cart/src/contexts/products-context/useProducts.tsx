import { useCallback } from 'react';

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
  } = useProductsContext();

  const fetchProducts = useCallback(() => {
    setIsFetching(true);
    getProducts().then((products: IProduct[]) => {
      setIsFetching(false);
      setProducts(products);
    });
  }, [setIsFetching, setProducts]);

  const filterProducts = useCallback((filters: string[]) => {
    setIsFetching(true);

    getProducts().then((products: IProduct[]) => {
      setIsFetching(false);
      let filteredProducts;
      console.time('filterProducts');

      if (filters && filters.length > 0) {
        const filterSet = new Set(filters);
        filteredProducts = products.filter((p: IProduct) =>
          p.availableSizes.some((size: string) => filterSet.has(size))
        );
      } else {
        filteredProducts = products;
      }

      console.timeEnd('filterProducts');
      setFilters(filters);
      setProducts(filteredProducts);
    });
  }, [setIsFetching, setFilters, setProducts]);

  return {
    isFetching,
    fetchProducts,
    products,
    filterProducts,
    filters,
  };
};

export default useProducts;
