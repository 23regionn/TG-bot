import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { createEntity, getEntities, partialUpdateCity } from 'app/entities/city/city.reducer';
import { InputText } from 'primereact/inputtext';
import {
  getSearchTypeInlineRequestCountByDates,
  getSearchTypePagesCategoryRequestCountByDates,
  getSearchTypePagesCategoryRequestCountForCityByDates,
  searchTypeLogCountByPageNumber,
  searchTypeLogCountByPageNumberByDates,
  searchTypeLogCountDetail,
} from 'app/entities/search-type-log/search-type-log.reducer';
import { Calendar } from 'primereact/calendar';
import { Button } from 'primereact/button';
import { Dropdown } from 'primereact/dropdown';
import { convertDateTimeToServer } from 'app/shared/util/date-utils';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';

export interface IAllStatisticsClicksProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllStatisticsClicks = (props: IAllStatisticsClicksProps) => {
  const [countTypeRequestDetail, setCountTypeRequestDetail] = useState(null);
  const [statisticsPageCount, setStatisticsPageCount] = useState(null);

  const [searchCountInlineRequestStartDate, setSearchCountInlineRequestStartDate] = useState<Date | Date[] | undefined>(undefined);
  const [searchCountInlineRequestEndDate, setSearchCountInlineRequestEndDate] = useState<Date | Date[] | undefined>(undefined);

  const [searchCountPagesCategoryRequestStartDate, setSearchCountPagesCategoryRequestStartDate] = useState<Date | Date[] | undefined>(
    undefined
  );
  const [searchCountPagesCategoryRequestEndDate, setSearchCountPagesCategoryRequestEndDate] = useState<Date | Date[] | undefined>(
    undefined
  );

  const [searchCountPagesCategoryRequestForCityStartDate, setSearchCountPagesCategoryRequestForCityStartDate] = useState<
    Date | Date[] | undefined
  >(undefined);
  const [searchCountPagesCategoryRequestForCityEndDate, setSearchCountPagesCategoryRequestForCityEndDate] = useState<
    Date | Date[] | undefined
  >(undefined);

  const [searchStatisticsPageCountByDatesStartDate, setSearchStatisticsPageCountByDatesStartDate] = useState<Date | Date[] | undefined>(
    undefined
  );
  const [searchStatisticsPageCountByDatesEndDate, setSearchStatisticsPageCountByDatesEndDate] = useState<Date | Date[] | undefined>(
    undefined
  );

  const [countInlineRequestsByDates, setCountInlineRequestsByDates] = useState(null);
  const [countPagesCategoryRequestByDates, setCountPagesCategoryRequestByDates] = useState(null);
  const [countPagesCategoryRequestForCityByDates, setCountPagesCategoryRequestForCityByDates] = useState(null);
  const [statisticsPageCountByDates, setStatisticsPageCountByDates] = useState(null);

  useEffect(() => {
    props.searchTypeLogCountDetail().then(count => {
      setCountTypeRequestDetail(count.value.data);
    });
    props.searchTypeLogCountByPageNumber().then(list => {
      setStatisticsPageCount(list.value.data);
    });
  }, []);

  const { cityList: cityList, match, loading } = props;

  const pushTo = rowData => {
    props.history.push({
      pathname: `all-categories/rel/by-city-id/${rowData.id}`,
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

  const searchCountInlineRequestByDateButton = () => {
    const entity = {
      startDate: convertDateTimeToServer(searchCountInlineRequestStartDate),
      endDate: convertDateTimeToServer(searchCountInlineRequestEndDate),
    };

    props.getSearchTypeInlineRequestCountByDates(entity).then(response => {
      setCountInlineRequestsByDates(response?.value?.data?.count);
    });
  };

  const searchCountPagesCategoryRequestByDateButton = () => {
    const entity = {
      startDate: convertDateTimeToServer(searchCountPagesCategoryRequestStartDate),
      endDate: convertDateTimeToServer(searchCountPagesCategoryRequestEndDate),
    };

    props.getSearchTypePagesCategoryRequestCountByDates(entity).then(response => {
      setCountPagesCategoryRequestByDates(response?.value?.data?.count);
    });
  };

  const searchCountPagesCategoryRequestForCityByDateButton = () => {
    const entity = {
      startDate: convertDateTimeToServer(searchCountPagesCategoryRequestForCityStartDate),
      endDate: convertDateTimeToServer(searchCountPagesCategoryRequestForCityEndDate),
    };

    props.getSearchTypePagesCategoryRequestCountForCityByDates(entity).then(response => {
      setCountPagesCategoryRequestForCityByDates(response?.value?.data?.count);
    });
  };

  const statisticsPageCountByDatesByDateButton = () => {
    const entity = {
      startDate: convertDateTimeToServer(searchStatisticsPageCountByDatesStartDate),
      endDate: convertDateTimeToServer(searchStatisticsPageCountByDatesEndDate),
    };

    props.searchTypeLogCountByPageNumberByDates(entity).then(response => {
      setStatisticsPageCountByDates(response?.value?.data);
    });
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '2rem' }}> Статитстика бота по нажатиям </div>
      </div>
      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество inline- запросов ⬇⬇⬇ </div>
        <div>
          <InputText value={countTypeRequestDetail?.countTypeLogCountInlineQuery} disabled={true} />
        </div>

        <hr />

        <div style={{ alignItems: 'left' }}>
          <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество inline- запросов на даты 🔍🔍🔍 </div>

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
              value={searchCountInlineRequestStartDate}
              onChange={e => setSearchCountInlineRequestStartDate(e.value)}
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
              value={searchCountInlineRequestEndDate}
              onChange={e => setSearchCountInlineRequestEndDate(e.value)}
              monthNavigator
              yearNavigator
              yearRange="2022:2050"
              monthNavigatorTemplate={monthNavigatorTemplate}
              yearNavigatorTemplate={yearNavigatorTemplate}
              dateFormat="dd.mm.yy"
            />
          </div>
          <br />
          <Button label="Поиск" icon="pi pi-search" onClick={searchCountInlineRequestByDateButton} />

          <br />
          <br />

          <div>
            <InputText value={countInlineRequestsByDates} disabled={true} />
          </div>
        </div>
      </div>

      <hr />

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество запросов категорий с листалками ⬇⬇⬇ </div>
        <div>
          <InputText value={countTypeRequestDetail?.countSearchTypeLogCountPagesCategoryQuery} disabled={true} />
        </div>

        <hr />

        <div style={{ alignItems: 'left' }}>
          <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество запросов категорий с листалками на даты 🔍🔍🔍 </div>

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
              value={searchCountPagesCategoryRequestStartDate}
              onChange={e => setSearchCountPagesCategoryRequestStartDate(e.value)}
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
              value={searchCountPagesCategoryRequestEndDate}
              onChange={e => setSearchCountPagesCategoryRequestEndDate(e.value)}
              monthNavigator
              yearNavigator
              yearRange="2022:2050"
              monthNavigatorTemplate={monthNavigatorTemplate}
              yearNavigatorTemplate={yearNavigatorTemplate}
              dateFormat="dd.mm.yy"
            />
          </div>
          <br />
          <Button label="Поиск" icon="pi pi-search" onClick={searchCountPagesCategoryRequestByDateButton} />

          <br />
          <br />

          <div>
            <InputText value={countPagesCategoryRequestByDates} disabled={true} />
          </div>
        </div>
      </div>

      <hr />

      <div style={{ alignItems: 'left' }}>
        <div style={{ textAlign: 'left', fontSize: '1.4rem' }}> Количество запросов категорий с листалками по городам ⬇⬇⬇ </div>
        <div>
          <InputText value={countTypeRequestDetail?.countSearchTypeLogCountPagesCategoryForCityQuery} disabled={true} />
        </div>

        <hr />

        <div style={{ alignItems: 'left' }}>
          <div style={{ textAlign: 'left', fontSize: '1.4rem' }}>
            {' '}
            Количество запросов категорий с листалками по городам на даты 🔍🔍🔍{' '}
          </div>

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
              value={searchCountPagesCategoryRequestForCityStartDate}
              onChange={e => setSearchCountPagesCategoryRequestForCityStartDate(e.value)}
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
              value={searchCountPagesCategoryRequestForCityEndDate}
              onChange={e => setSearchCountPagesCategoryRequestForCityEndDate(e.value)}
              monthNavigator
              yearNavigator
              yearRange="2022:2050"
              monthNavigatorTemplate={monthNavigatorTemplate}
              yearNavigatorTemplate={yearNavigatorTemplate}
              dateFormat="dd.mm.yy"
            />
          </div>
          <br />
          <Button label="Поиск" icon="pi pi-search" onClick={searchCountPagesCategoryRequestForCityByDateButton} />

          <br />
          <br />

          <div>
            <InputText value={countPagesCategoryRequestForCityByDates} disabled={true} />
          </div>
        </div>
      </div>

      <hr />

      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '2rem' }}> Статитстика бота по нажатиям на листалку </div>
      </div>

      <br />

      <DataTable value={statisticsPageCount as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="pageNumber" sortable header="№ страницы листалки"></Column>
        <Column field="count" sortable header="Количество нажатий"></Column>
        {/*<Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>*/}
      </DataTable>

      <hr />

      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '2rem' }}> Статитстика бота по нажатиям на листалку на даты </div>
      </div>

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
          value={searchStatisticsPageCountByDatesStartDate}
          onChange={e => setSearchStatisticsPageCountByDatesStartDate(e.value)}
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
          value={searchStatisticsPageCountByDatesEndDate}
          onChange={e => setSearchStatisticsPageCountByDatesEndDate(e.value)}
          monthNavigator
          yearNavigator
          yearRange="2022:2050"
          monthNavigatorTemplate={monthNavigatorTemplate}
          yearNavigatorTemplate={yearNavigatorTemplate}
          dateFormat="dd.mm.yy"
        />
      </div>
      <br />
      <Button label="Поиск" icon="pi pi-search" onClick={statisticsPageCountByDatesByDateButton} />

      <br />

      <DataTable value={statisticsPageCountByDates as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="pageNumber" sortable header="№ страницы листалки"></Column>
        <Column field="count" sortable header="Количество нажатий"></Column>
        {/*<Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>*/}
      </DataTable>
    </div>
  );
};

const mapStateToProps = ({ city }: IRootState) => ({
  cityList: city.entities,
  loading: city.loading,
});

const mapDispatchToProps = {
  searchTypeLogCountDetail,
  getSearchTypeInlineRequestCountByDates,
  getSearchTypePagesCategoryRequestCountByDates,
  getSearchTypePagesCategoryRequestCountForCityByDates,
  searchTypeLogCountByPageNumberByDates,
  searchTypeLogCountByPageNumber,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllStatisticsClicks);
