import { createContext, useContext, FC, useState } from 'react';

import { IProduct } from 'models';

export interface IProductsContext {
  isFetching: boolean;
  setIsFetching(state: boolean): void;
  products: IProduct[];
  setProducts(products: IProduct[]): void;
  filters: string[];
  setFilters(filters: string[]): void;
  error: string | null;
  setError(error: string | null): void;
}

const ProductsContext = createContext<IProductsContext | undefined>(undefined);
const useProductsContext = (): IProductsContext => {
  const context = useContext(ProductsContext);

  if (!context) {
    throw new Error(
      'useProductsContext must be used within a ProductsProvider'
    );
  }

  return context;
};

const ProductsProvider: FC = (props) => {
  const [isFetching, setIsFetching] = useState(false);
  const [products, setProducts] = useState<IProduct[]>([]);
  const [filters, setFilters] = useState<string[]>([]);
  const [error, setError] = useState<string | null>(null);

  const ProductContextValue: IProductsContext = {
    isFetching,
    setIsFetching,
    products,
    setProducts,
    filters,
    setFilters,
    error,
    setError,
  };

  return <ProductsContext.Provider value={ProductContextValue} {...props} />;
};

export { ProductsProvider, useProductsContext };
