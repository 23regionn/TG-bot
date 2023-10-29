import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { createEntity, getEntities, partialUpdateCity } from 'app/entities/city/city.reducer';

export interface IAllStatisticsCityProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllStatisticsCity = (props: IAllStatisticsCityProps) => {
  useEffect(() => {
    // props.getEntities();
  }, []);

  const { cityList: cityList, match, loading } = props;

  const pushTo = rowData => {
    props.history.push({
      pathname: `all-categories/rel/by-city-id/${rowData.id}`,
    });
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '2rem' }}> Статитстика бота по городам </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ city }: IRootState) => ({
  cityList: city.entities,
  loading: city.loading,
});

const mapDispatchToProps = {
  getEntities,
  createEntity,
  partialUpdateCity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllStatisticsCity);
