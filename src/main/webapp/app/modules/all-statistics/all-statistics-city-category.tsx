import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { getCategoriesStatisticsForCity, getStatisticsCategoryLogForCityByDates } from 'app/entities/category-log/category-log.reducer';
import { Button } from 'primereact/button';
import { Calendar } from 'primereact/calendar';
import { InputText } from 'primereact/inputtext';
import { Dialog } from 'primereact/dialog';
import { Dropdown } from 'primereact/dropdown';
import { convertDateTimeToServer } from 'app/shared/util/date-utils';

export interface IAllStatisticsCityCategoryProps
  extends StateProps,
    DispatchProps,
    RouteComponentProps<{
      url: string;
      idCity: string;
    }> {}

export const AllStatisticsCityCategory = (props: IAllStatisticsCityCategoryProps) => {
  const [statisticsCategoriesLogs, setStatisticsCategoriesLogs] = useState(null);
  const [statisticsCategoriesLogsByDate, setStatisticsCategoriesLogsByDate] = useState(null);
  const [openDateDetailDialog, setOpenDateDetailDialog] = useState(false);
  const [logEntity, setLogEntity] = useState(null);

  const [searchCountClickStartDate, setSearchCountClickStartDate] = useState<Date | Date[] | undefined>(undefined);
  const [searchCountClickEndDate, setSearchCountClickEndDate] = useState<Date | Date[] | undefined>(undefined);

  useEffect(() => {
    props.getCategoriesStatisticsForCity(props.match.params.idCity).then(logs => {
      setStatisticsCategoriesLogs(logs.value.data);
    });
  }, []);

  const { cityList: cityList, match, loading } = props;

  const showByDates = rowData => {
    setLogEntity(rowData);
    setOpenDateDetailDialog(true);
  };

  const hideDateDialog = () => {
    setLogEntity(null);
    setStatisticsCategoriesLogsByDate(null);
    setOpenDateDetailDialog(false);
  };

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-search" className="p-button-rounded p-button-success p-mr-2" onClick={() => showByDates(rowData)} />
      </React.Fragment>
    );
  };

  const dateDialogFooter = (
    <React.Fragment>
      <Button label="Close" icon="pi pi-times" className="p-button-text" onClick={hideDateDialog} />
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

  const searchCategoryStatisticsByDateButton = () => {
    const entity = {
      idCategory: logEntity?.idCategory,
      idCity: logEntity?.idCity,
      startDate: convertDateTimeToServer(searchCountClickStartDate),
      endDate: convertDateTimeToServer(searchCountClickEndDate),
    };

    props.getStatisticsCategoryLogForCityByDates(entity).then(categoryLogsStatistic => {
      setStatisticsCategoriesLogsByDate(categoryLogsStatistic?.value.data);
    });
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.5rem' }}>
          {' '}
          Статитстика категорий по городу &nbsp;
          {/*{statisticsCategoriesLogs[0]?.cityName}*/}
        </div>
      </div>

      <br />

      <DataTable value={statisticsCategoriesLogs as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="idCategory" header="№"></Column>
        <Column field="categoryName" filter filterPlaceholder="Поиск на наименованию" sortable header="Имя категории"></Column>
        <Column field="countClickTotalByUniChatId" sortable header="Количество уникальных пользователей"></Column>
        <Column field="countClickByCity" sortable header="Количество кликов по городу"></Column>
        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={openDateDetailDialog}
        style={{ width: '600px' }}
        header={'Статистика ' + logEntity?.categoryName + ' на даты'}
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

          <label htmlFor="navigatorstemplate">
            {' '}
            Начальная дата <span>&nbsp;</span>{' '}
          </label>
          <Calendar
            id="navigatorstemplate"
            value={searchCountClickStartDate}
            onChange={e => setSearchCountClickStartDate(e.value)}
            monthNavigator
            yearNavigator
            yearRange="2022:2050"
            monthNavigatorTemplate={monthNavigatorTemplate}
            yearNavigatorTemplate={yearNavigatorTemplate}
            dateFormat="dd.mm.yy"
          />

          <br />
          <label htmlFor="navigatorstemplate">
            {' '}
            Конечная дата <span>&nbsp;</span>{' '}
          </label>
          <Calendar
            id="navigatorstemplate"
            value={searchCountClickEndDate}
            onChange={e => setSearchCountClickEndDate(e.value)}
            monthNavigator
            yearNavigator
            yearRange="2022:2050"
            monthNavigatorTemplate={monthNavigatorTemplate}
            yearNavigatorTemplate={yearNavigatorTemplate}
            dateFormat="dd.mm.yy"
          />
          <br />
          <Button label="Поиск" icon="pi pi-search" onClick={searchCategoryStatisticsByDateButton} />
        </div>

        <br />

        <div className="p-field">
          <div> Всего нажатий </div>
          <InputText value={statisticsCategoriesLogsByDate?.countClickByCity || ''} disabled />
        </div>

        <br />

        <div className="p-field">
          <div> Всего уникальных нажатий </div>
          <InputText value={statisticsCategoriesLogsByDate?.countClickTotalByUniChatId || ''} disabled />
        </div>

        <br />
      </Dialog>
    </div>
  );
};

const mapStateToProps = ({ city }: IRootState) => ({
  cityList: city.entities,
  loading: city.loading,
});

const mapDispatchToProps = {
  getCategoriesStatisticsForCity,
  getStatisticsCategoryLogForCityByDates,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllStatisticsCityCategory);
