import './loader-component.scss';
import React from 'react';
import { ProgressSpinner } from 'primereact/progressspinner';

export const LoaderComponent = () => {
  return (
    <div className="loader-component-style">
      <ProgressSpinner className="loader" animationDuration=".5s" />
    </div>
  );
};
