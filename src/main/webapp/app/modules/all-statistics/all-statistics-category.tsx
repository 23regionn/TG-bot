import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { getCategoriesStatistics, getStatisticsCategoryLogByDates } from 'app/entities/category-log/category-log.reducer';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { InputText } from 'primereact/inputtext';
import { Calendar } from 'primereact/calendar';
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import { convertDateTimeToServer } from 'app/shared/util/date-utils';
import { Dropdown } from 'primereact/dropdown';

export interface IAllStatisticsCategoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllStatisticsCategory = (props: IAllStatisticsCategoryProps) => {
  const [statiscticsCategoriesLogs, setStatiscticsCategoriesLogs] = useState(null);
  const [openDateDetailDialog, setOpenDateDetailDialog] = useState(false);
  const [logEntity, setLogEntity] = useState(null);
  const [searchStartDate, setSearchStartDate] = useState<Date | Date[] | undefined>(undefined);
  const [searchEndDate, setSearchEndDate] = useState<Date | Date[] | undefined>(undefined);

  const [categoryName, setCategoryName] = useState(null);
  const [countClickTotal, setCountClickTotal] = useState(null);
  const [countClickTotalByUniChatId, setCountClickTotalByUniChatId] = useState(null);
  const [countClickByCity, setCountClickByCity] = useState(null);
  const [countClickNotByCity, setCountClickNotByCity] = useState(null);

  useEffect(() => {
    props.getCategoriesStatistics().then(categoryLogs => {
      setStatiscticsCategoriesLogs(categoryLogs.value.data);
    });
  }, []);

  const { cityList: cityList, match, loading } = props;

  const showByDates = rowData => {
    setLogEntity(rowData);
    setCategoryName(rowData?.categoryName);
    setCountClickTotal(rowData?.countClickTotal);
    setCountClickTotalByUniChatId(rowData?.countClickTotalByUniChatId);
    setCountClickByCity(rowData?.countClickByCity);
    setCountClickNotByCity(rowData?.countClickNotByCity);
    setOpenDateDetailDialog(true);
  };

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-search" className="p-button-rounded p-button-success p-mr-2" onClick={() => showByDates(rowData)} />
      </React.Fragment>
    );
  };

  const hideDateDialog = () => {
    setLogEntity(null);
    setCategoryName(null);
    setCountClickTotal(null);
    setCountClickTotalByUniChatId(null);
    setCountClickByCity(null);
    setCountClickNotByCity(null);
    setSearchStartDate(undefined);
    setSearchEndDate(undefined);
    setOpenDateDetailDialog(false);
  };

  const dateDialogFooter = (
    <React.Fragment>
      <Button label="Close" icon="pi pi-times" className="p-button-text" onClick={hideDateDialog} />
    </React.Fragment>
  );

  const searchCategoryStatisticsByDateButton = () => {
    const entity = {
      id: logEntity?.idCategory,
      startDate: convertDateTimeToServer(searchStartDate),
      endDate: convertDateTimeToServer(searchEndDate),
    };

    props.getStatisticsCategoryLogByDates(entity).then(categoryLogsStatistic => {
      setCountClickTotal(categoryLogsStatistic?.value.data?.countClickTotal);
      setCountClickTotalByUniChatId(categoryLogsStatistic?.value.data?.countClickTotalByUniChatId);
      setCountClickByCity(categoryLogsStatistic?.value.data?.countClickByCity);
      setCountClickNotByCity(categoryLogsStatistic?.value.data?.countClickNotByCity);
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

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '2rem' }}> Статитстика бота по категориям </div>
      </div>

      <br />

      <DataTable value={statiscticsCategoriesLogs as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="idCategory" header="№ Категории"></Column>
        <Column field="categoryName" filter filterPlaceholder="Поиск на наименованию" sortable header="Наименование категории"></Column>
        <Column
          field="countClickTotal"
          sortable
          header="Всего нажатий"
          // style={{ width: '7.5vw' }}
          body={rowData => (rowData.countClickTotal !== null ? rowData.countClickTotal : 'null')}
        ></Column>
        <Column
          field="countClickTotalByUniChatId"
          sortable
          header="Всего уникальных нажатий"
          // style={{ width: '7.5vw' }}
          body={rowData => (rowData.countClickTotalByUniChatId !== null ? rowData.countClickTotalByUniChatId : 'null')}
        ></Column>
        <Column
          field="countClickByCity"
          sortable
          header="Всего нажатий в городах"
          // style={{ width: '7.5vw' }}
          body={rowData => (rowData.countClickByCity !== null ? rowData.countClickByCity : 'null')}
        ></Column>
        <Column
          field="countClickNotByCity"
          sortable
          header="Всего нажатий не в городах"
          // style={{ width: '7.5vw' }}
          body={rowData => (rowData.countClickNotByCity !== null ? rowData.countClickNotByCity : 'null')}
        ></Column>
        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={openDateDetailDialog}
        style={{ width: '600px' }}
        header={'Cтатистика: ' + categoryName}
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
            value={searchStartDate}
            onChange={e => setSearchStartDate(e.value)}
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
            value={searchEndDate}
            onChange={e => setSearchEndDate(e.value)}
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
          <InputText value={countClickTotal || ''} disabled />
        </div>

        <br />

        <div className="p-field">
          <div> Всего уникальных нажатий </div>
          <InputText value={countClickTotalByUniChatId || ''} disabled />
        </div>

        <br />

        <div className="p-field">
          <div> Всего нажатий не в городах </div>
          <InputText value={countClickNotByCity || ''} disabled />
        </div>

        <br />

        <div className="p-field">
          <div> Всего нажатий в городах </div>
          <InputText value={countClickByCity || ''} disabled />
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
  getCategoriesStatistics,
  getStatisticsCategoryLogByDates,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllStatisticsCategory);
