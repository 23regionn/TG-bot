import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { Calendar } from 'primereact/calendar';
import { getCountSubscribersByDates, getTGUserCount } from 'app/entities/tg-user/tg-user.reducer';
import { Dropdown } from 'primereact/dropdown';
import { convertDateTimeToServer } from 'app/shared/util/date-utils';
import { getAllSearchTypeLogsCount, getSearchTypeByDates } from 'app/entities/search-type-log/search-type-log.reducer';

export interface IAllStatisticsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllStatistics = (props: IAllStatisticsProps) => {
  const [countTgUsers, setCountTgUsers] = useState(null);
  const [countTgUsersByDates, setCountTgUsersByDates] = useState(null);

  const [countSearchRequest, setCountSearchRequest] = useState(null);
  const [countSearchRequestByDates, setCountSearchRequestByDates] = useState(null);

  const [searchCountUsersStartDate, setSearchCountUsersStartDate] = useState<Date | Date[] | undefined>(undefined);
  const [searchCountUsersEndDate, setSearchCountUsersEndDate] = useState<Date | Date[] | undefined>(undefined);
  const [searchCountRequestStartDate, setSearchCountRequestStartDate] = useState<Date | Date[] | undefined>(undefined);
  const [searchCountRequestEndDate, setSearchCountRequestEndDate] = useState<Date | Date[] | undefined>(undefined);

  useEffect(() => {
    props.getTGUserCount().then(count => {
      setCountTgUsers(count.value.data);
    });
    props.getAllSearchTypeLogsCount().then(count => {
      setCountSearchRequest(count.value.data);
    });
  }, []);

  const { cityList: cityList, match, loading } = props;

  const pushToStatisticsCategory = rowData => {
    props.history.push({
      pathname: `all-statistics/category`,
    });
  };

  const pushToStatisticsTgUsers = rowData => {
    props.history.push({
      pathname: `all-statistics/tgUsers`,
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

  const monthNavigatorTemplate = (e: any) => {
    return (
      <Dropdown
        value={e.value}
        options={e.options}
        onChange={event => e.onChange(event.originalEvent, event.value)}
        style={{ lineHeight: 1 }}
      />
    );
  };

  const yearNavigatorTemplate = (e: any) => {
    return (
      <Dropdown
        value={e.value}
        options={e.options}
        onChange={event => e.onChange(event.originalEvent, event.value)}
        className="p-ml-2"
        style={{ lineHeight: 1 }}
      />
    );
  };

  const searchCountUsersByDateButton = () => {
    const entity = {
      startDate: convertDateTimeToServer(searchCountUsersStartDate),
      endDate: convertDateTimeToServer(searchCountUsersEndDate),
    };

    props.getCountSubscribersByDates(entity).then(response => {
      setCountTgUsersByDates(response?.value?.data?.count);
    });
  };

  const searchTypeByDateButton = () => {
    const entity = {
      startDate: convertDateTimeToServer(searchCountRequestStartDate),
      endDate: convertDateTimeToServer(searchCountRequestEndDate),
    };

    props.getSearchTypeByDates(entity).then(response => {
      setCountSearchRequestByDates(response?.value?.data?.count);
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
          <InputText value={countTgUsers} disabled={true} />
        </div>
      </div>

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество пользователей на даты 🔍🔍🔍 </div>

        <div className="p-field">
          <label htmlFor="range" style={{ fontWeight: '600' }}>
            Выберите даты:
          </label>

          <br />

          <div>
            {' '}
            Начальная дата <span>&nbsp;</span>{' '}
          </div>
          <Calendar
            id="navigatorstemplate"
            value={searchCountUsersStartDate}
            onChange={e => setSearchCountUsersStartDate(e.value)}
            monthNavigator
            yearNavigator
            yearRange="2022:2050"
            monthNavigatorTemplate={monthNavigatorTemplate}
            yearNavigatorTemplate={yearNavigatorTemplate}
            dateFormat="dd.mm.yy"
          />

          <br />
          <div>
            {' '}
            Конечная дата <span>&nbsp; &nbsp;</span>{' '}
          </div>
          <Calendar
            id="navigatorstemplate"
            value={searchCountUsersEndDate}
            onChange={e => setSearchCountUsersEndDate(e.value)}
            monthNavigator
            yearNavigator
            yearRange="2022:2050"
            monthNavigatorTemplate={monthNavigatorTemplate}
            yearNavigatorTemplate={yearNavigatorTemplate}
            dateFormat="dd.mm.yy"
          />
        </div>
        <br />
        <Button label="Поиск" icon="pi pi-search" onClick={searchCountUsersByDateButton} />

        <br />
        <br />

        <div>
          <InputText value={countTgUsersByDates} disabled={true} />
        </div>
      </div>

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество запросов ❓❓❓ </div>
        <div>
          <InputText value={countSearchRequest} disabled={true} />
        </div>
      </div>

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество запросов на даты 🔍🔍🔍 </div>

        <div className="p-field">
          <label htmlFor="range" style={{ fontWeight: '600' }}>
            Выберите даты:
          </label>

          <br />

          <div>
            {' '}
            Начальная дата <span>&nbsp;</span>{' '}
          </div>
          <Calendar
            id="navigatorstemplate"
            value={searchCountRequestStartDate}
            onChange={e => setSearchCountRequestStartDate(e.value)}
            monthNavigator
            yearNavigator
            yearRange="2022:2050"
            monthNavigatorTemplate={monthNavigatorTemplate}
            yearNavigatorTemplate={yearNavigatorTemplate}
            dateFormat="dd.mm.yy"
          />

          <br />
          <div>
            {' '}
            Конечная дата <span>&nbsp; &nbsp;</span>{' '}
          </div>
          <Calendar
            id="navigatorstemplate"
            value={searchCountRequestEndDate}
            onChange={e => setSearchCountRequestEndDate(e.value)}
            monthNavigator
            yearNavigator
            yearRange="2022:2050"
            monthNavigatorTemplate={monthNavigatorTemplate}
            yearNavigatorTemplate={yearNavigatorTemplate}
            dateFormat="dd.mm.yy"
          />
        </div>
        <br />
        <Button label="Поиск" icon="pi pi-search" onClick={searchTypeByDateButton} />

        <br />
        <br />

        <div>
          <InputText value={countSearchRequestByDates} disabled={true} />
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
          <Button id="button_basic" label="Статистика по пользователям" onClick={pushToStatisticsTgUsers} />
        </div>
        <div style={{ flexGrow: 4, textAlign: 'center', fontSize: '1.4rem' }}>
          <Button id="button_basic" label="Статистика по нажатиям" onClick={pushToStatisticsClicks} />
        </div>
      </div>

      <hr />
    </div>
  );
};

const mapStateToProps = ({ city }: IRootState) => ({
  cityList: city.entities,
  loading: city.loading,
});

const mapDispatchToProps = {
  getTGUserCount,
  getCountSubscribersByDates,
  getAllSearchTypeLogsCount,
  getSearchTypeByDates,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllStatistics);
