import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import {
  getAllCityBaseStatistics,
  getCityBaseStatisticsByDate,
  getCityBaseStatisticsByDateForCity,
} from 'app/entities/search-type-log/search-type-log.reducer';
import { Button } from 'primereact/button';
import { Calendar } from 'primereact/calendar';
import { InputText } from 'primereact/inputtext';
import { Dialog } from 'primereact/dialog';
import { Dropdown } from 'primereact/dropdown';
import { convertDateTimeToServer } from 'app/shared/util/date-utils';
import { NavLink } from 'reactstrap';

export interface IAllStatisticsCityProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllStatisticsCity = (props: IAllStatisticsCityProps) => {
  const [cityStatistics, setCityStatistics] = useState(null);
  const [cityStatisticsByDates, setCityStatisticsByDates] = useState(null);
  const [cityName, setCityName] = useState(null);
  const [idCityState, setIdCityState] = useState(null);
  const [openDateDetailDialog, setOpenDateDetailDialog] = useState(false);
  const [openPageClickDialog, setOpenPageClickDialog] = useState(false);
  const [cityStatisticsPageCountByDates, setCityStatisticsPageCountByDates] = useState(null);

  const [cityStatisticsByDatesStartDate, setCityStatisticsByDatesStartDate] = useState<Date | Date[] | undefined>(undefined);
  const [cityStatisticsByDatesEndDate, setCityStatisticsByDatesEndDate] = useState<Date | Date[] | undefined>(undefined);

  const [cityStatisticsClickPageByDatesStartDate, setCityStatisticsClickPageByDatesStartDate] = useState<Date | Date[] | undefined>(
    undefined
  );
  const [cityStatisticsClickPageByDatesEndDate, setCityStatisticsClickPageByDatesEndDate] = useState<Date | Date[] | undefined>(undefined);

  useEffect(() => {
    props.getAllCityBaseStatistics().then(cities => {
      setCityStatistics(cities.value.data);
    });
  }, []);

  const { cityList: cityList, match, loading } = props;

  const pushToCategoryStatistics = rowData => {
    props.history.push({
      pathname: `/category/${rowData?.idCity}`,
    });
  };

  const showByDates = rowData => {
    setIdCityState(rowData?.idCity);
    setCityName(rowData?.nameCity);
    setOpenDateDetailDialog(true);
  };

  const showPageClick = rowData => {
    setIdCityState(rowData?.idCity);
    setCityName(rowData?.nameCity);
    setOpenPageClickDialog(true);
  };

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-search" className="p-button-rounded p-button-success p-mr-2" onClick={() => showByDates(rowData)} />
        <Button label="Листалка" className="p-button-text" onClick={() => showPageClick(rowData)} />
        <NavLink tag={Link} to={`/all-statistics/city/category/${rowData?.idCity}`} className="d-flex align-items-center">
          <Button label="Категории" className="p-button-text" />
        </NavLink>
      </React.Fragment>
    );
  };

  const hideDateDialog = () => {
    setCityName(null);
    setIdCityState(null);
    setCityStatisticsByDates(null);
    setCityStatisticsByDatesStartDate(undefined);
    setCityStatisticsByDatesEndDate(undefined);
    setOpenDateDetailDialog(false);
  };

  const hidePageClickDialog = () => {
    setCityName(null);
    setIdCityState(null);
    setCityStatisticsPageCountByDates(null);
    setCityStatisticsClickPageByDatesStartDate(undefined);
    setCityStatisticsClickPageByDatesEndDate(undefined);
    setOpenPageClickDialog(false);
  };

  const dateDialogFooter = (
    <React.Fragment>
      <Button label="Close" icon="pi pi-times" className="p-button-text" onClick={hideDateDialog} />
    </React.Fragment>
  );

  const pageClickDialogFooter = (
    <React.Fragment>
      <Button label="Close" icon="pi pi-times" className="p-button-text" onClick={hidePageClickDialog} />
    </React.Fragment>
  );

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

  const statisticsPageCountByDatesByDateButton = () => {
    const entity = {
      idCity: idCityState,
      startDate: convertDateTimeToServer(cityStatisticsByDatesStartDate),
      endDate: convertDateTimeToServer(cityStatisticsByDatesEndDate),
    };

    props.getCityBaseStatisticsByDate(entity).then(response => {
      setCityStatisticsByDates(response?.value?.data);
    });
  };

  const statisticsClickPageForCityByDateButton = () => {
    const entity = {
      idCity: idCityState,
      startDate: convertDateTimeToServer(cityStatisticsClickPageByDatesStartDate),
      endDate: convertDateTimeToServer(cityStatisticsClickPageByDatesEndDate),
    };
    props.getCityBaseStatisticsByDateForCity(entity).then(response => {
      setCityStatisticsPageCountByDates(response?.value?.data);
    });
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '2rem' }}> Статитстика бота по городам </div>
      </div>

      <br />

      <DataTable value={cityStatistics as any[]} className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="idCity" header="№"></Column>
        <Column field="nameCity" filter filterPlaceholder="Поиск на названию" sortable header="Наименование города"></Column>
        <Column field="countClickFirstPage" header="Количество кликов по первой страничке" sortable></Column>
        <Column field="countClickUsers" header="Количество кликавших пользователей"></Column>
        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={openDateDetailDialog}
        style={{ width: '600px' }}
        header={'Cтатистика кликов по городу : ' + cityName + ' на даты'}
        modal
        className="p-fluid"
        footer={dateDialogFooter}
        onHide={hideDateDialog}
        dismissableMask={true}
      >
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
            value={cityStatisticsByDatesStartDate}
            onChange={e => setCityStatisticsByDatesStartDate(e.value)}
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
            value={cityStatisticsByDatesEndDate}
            onChange={e => setCityStatisticsByDatesEndDate(e.value)}
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

        <label htmlFor="range" style={{ fontWeight: '600' }}>
          Количество кликов по первой страничке
        </label>
        <div>
          <InputText value={cityStatisticsByDates?.countClickFirstPage} disabled={true} />
        </div>

        <label htmlFor="range" style={{ fontWeight: '600' }}>
          Количество кликавших пользователей
        </label>
        <div>
          <InputText value={cityStatisticsByDates?.countClickUsers} disabled={true} />
        </div>
      </Dialog>

      <Dialog
        visible={openPageClickDialog}
        style={{ width: '600px' }}
        header={'Cтатистика долистываний категорий по городу : ' + cityName + ' на даты'}
        modal
        className="p-fluid"
        footer={pageClickDialogFooter}
        onHide={hidePageClickDialog}
        dismissableMask={true}
      >
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
            value={cityStatisticsClickPageByDatesStartDate}
            onChange={e => setCityStatisticsClickPageByDatesStartDate(e.value)}
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
            value={cityStatisticsClickPageByDatesEndDate}
            onChange={e => setCityStatisticsClickPageByDatesEndDate(e.value)}
            monthNavigator
            yearNavigator
            yearRange="2022:2050"
            monthNavigatorTemplate={monthNavigatorTemplate}
            yearNavigatorTemplate={yearNavigatorTemplate}
            dateFormat="dd.mm.yy"
          />
        </div>
        <br />
        <Button label="Поиск" icon="pi pi-search" onClick={statisticsClickPageForCityByDateButton} />

        <DataTable value={cityStatisticsPageCountByDates as any[]} sortMode="multiple" className="oi-p-datatable">
          <Column headerStyle={{ width: '5rem' }} field="pageNumber" sortable header="№ страницы листалки"></Column>
          <Column field="count" sortable header="Количество нажатий"></Column>
        </DataTable>
      </Dialog>
    </div>
  );
};

const mapStateToProps = ({ city }: IRootState) => ({
  cityList: city.entities,
  loading: city.loading,
});

const mapDispatchToProps = {
  getAllCityBaseStatistics,
  getCityBaseStatisticsByDate,
  getCityBaseStatisticsByDateForCity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllStatisticsCity);
