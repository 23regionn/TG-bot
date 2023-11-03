import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { createEntity, getEntities, partialUpdateCity } from 'app/entities/city/city.reducer';
import { Calendar } from 'primereact/calendar';

export interface IAllStatisticsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllStatistics = (props: IAllStatisticsProps) => {
  const [dateForSearchCountSubscribers, setDateForSearchCountSubscribers] = useState<Date | Date[] | undefined>(undefined);
  const [dateForSearchCountRequests, setDateForSearchCountRequests] = useState<Date | Date[] | undefined>(undefined);

  useEffect(() => {
    // props.getEntities();
  }, []);

  useEffect(() => {
    window.console.log(new Date(dateForSearchCountSubscribers?.[0]).toLocaleDateString(), 'dateForSearchCountSubscribers-0');
    window.console.log(new Date(dateForSearchCountSubscribers?.[1]).toLocaleDateString(), 'dateForSearchCountSubscribers-1');
  }, [dateForSearchCountSubscribers?.[1]]);

  const { cityList: cityList, match, loading } = props;

  const pushToStatisticsCategory = rowData => {
    props.history.push({
      pathname: `all-statistics/category`,
    });
  };

  const pushToStatisticsChannels = rowData => {
    props.history.push({
      pathname: `all-statistics/channels`,
    });
  };

  const pushToStatisticsCity = rowData => {
    props.history.push({
      pathname: `all-statistics/city`,
    });
  };

  const pushToStatisticsClicks = rowData => {
    props.history.push({
      pathname: `all-statistics/clicks`,
    });
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '2rem' }}> Статитстика бота </div>
      </div>
      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество пользователей ⬇⬇⬇ </div>
        <div>
          <InputText defaultValue={'1'} disabled={true} />
        </div>
      </div>

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество пользователей на даты 🔍🔍🔍 </div>

        <div className="p-field p-col-12 p-md-4">
          <label htmlFor="range">
            Выберите даты - <span>&nbsp;</span>{' '}
          </label>
          <Calendar
            id="range"
            value={dateForSearchCountSubscribers}
            onChange={e => setDateForSearchCountSubscribers(e.value)}
            selectionMode="range"
            readOnlyInput
            dateFormat="dd.mm.yy"
          />
          <Button
            label="Поиск"
            icon="pi pi-search"
            className="p-button-text"
            // onClick={hideDialog}
          />
        </div>

        <div>
          <InputText defaultValue={'1'} disabled={true} />
        </div>
      </div>

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество запросов ❓❓❓ </div>
        <div>
          <InputText defaultValue={'1'} disabled={true} />
        </div>
      </div>

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество запросов на даты 🔍🔍🔍 </div>

        <div className="p-field p-col-12 p-md-4">
          <label htmlFor="range">
            Выберите даты - <span>&nbsp;</span>{' '}
          </label>
          <Calendar
            id="range"
            value={dateForSearchCountRequests}
            onChange={e => setDateForSearchCountRequests(e.value)}
            selectionMode="range"
            readOnlyInput
          />
          <Button
            label="Поиск"
            icon="pi pi-search"
            className="p-button-text"
            // onClick={hideDialog}
          />
        </div>

        <div>
          <InputText defaultValue={'1'} disabled={true} />
        </div>
      </div>

      <hr />
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>
          <Button id="button_basic" label="Статистика по категориям" onClick={pushToStatisticsCategory} />
        </div>

        <div style={{ flexGrow: 2, textAlign: 'center', fontSize: '1.4rem' }}>
          <Button id="button_basic" label="Статистика по городам" onClick={pushToStatisticsCity} />
        </div>

        <div style={{ flexGrow: 3, textAlign: 'center', fontSize: '1.4rem' }}>
          <Button id="button_basic" label="Статистика по каналам" onClick={pushToStatisticsChannels} />
        </div>
        <div style={{ flexGrow: 4, textAlign: 'center', fontSize: '1.4rem' }}>
          <Button id="button_basic" label="Статистика по нажатиям" onClick={pushToStatisticsClicks} />
        </div>
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

export default connect(mapStateToProps, mapDispatchToProps)(AllStatistics);
