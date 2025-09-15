import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { getAllTgUsersForStatistics } from 'app/entities/tg-user/tg-user.reducer';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Calendar } from 'primereact/calendar';
import { Dialog } from 'primereact/dialog';
import { getStatisticCategoryByChatId, getStatisticCityByChatId } from 'app/entities/category-log/category-log.reducer';
import { NavLink } from 'reactstrap';

export interface IAllStatisticsTgUsersProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllStatisticsTgUsers = (props: IAllStatisticsTgUsersProps) => {
  const [statisticsTgUsers, setStatisticsTgUsers] = useState(null);
  const [openCategoriesDialog, setOpenCategoriesDialog] = useState(false);
  const [openCitiesDialog, setOpenCitiesDialog] = useState(false);
  const [logEntity, setLogEntity] = useState(null);

  const [citiesForUser, setCitiesForUser] = useState(null);
  const [categoriesForUser, setCategoriesForUser] = useState(null);

  useEffect(() => {
    props.getAllTgUsersForStatistics().then(users => {
      setStatisticsTgUsers(users.value.data);
    });
  }, []);

  const { cityList, match, loading } = props;

  const showCategoriesClick = rowData => {
    setLogEntity(rowData);
    setOpenCategoriesDialog(true);

    props.getStatisticCategoryByChatId(rowData?.chatId).then(categories => {
      setCategoriesForUser(categories.value.data);
    });
  };

  const showCitiesClick = rowData => {
    setLogEntity(rowData);
    setOpenCitiesDialog(true);

    props.getStatisticCityByChatId(rowData?.chatId).then(cities => {
      setCitiesForUser(cities.value.data);
    });
  };

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button label="Категории" className="p-button-text" onClick={() => showCategoriesClick(rowData)} />
        <Button label="Города" className="p-button-text" onClick={() => showCitiesClick(rowData)} />
      </React.Fragment>
    );
  };

  const hideCategoriesDialog = () => {
    setLogEntity(null);
    setCategoriesForUser(null);
    setOpenCategoriesDialog(false);
  };
  const hideCitiesDialog = () => {
    setLogEntity(null);
    setCitiesForUser(null);
    setOpenCitiesDialog(false);
  };

  const citiesDialogFooter = (
    <React.Fragment>
      <Button label="Close" icon="pi pi-times" className="p-button-text" onClick={hideCitiesDialog} />
    </React.Fragment>
  );

  const categoriesDialogFooter = (
    <React.Fragment>
      <Button label="Close" icon="pi pi-times" className="p-button-text" onClick={hideCategoriesDialog} />
    </React.Fragment>
  );

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}> Статистика бота по пользователям </div>
      </div>

      <br />

      <DataTable value={statisticsTgUsers as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="id" header="№"></Column>
        <Column field="firstName" filter filterPlaceholder="Поиск по First name" sortable header="First name"></Column>
        <Column
          field="userName"
          filter
          filterPlaceholder="Поиск по User name"
          header="User name"
          body={rowData => (
            <NavLink href={`https://t.me/${rowData?.userName}`} target="_blank">
              {rowData?.userName}
            </NavLink>
          )}
        ></Column>
        <Column field="chatId" filter filterPlaceholder="Поиск по chatId" header="chatId"></Column>
        <Column
          field="registrationDate"
          header="Дата регистрации"
          sortable
          dataType="date"
          body={rowData => new Date(rowData.registrationDate).toLocaleDateString()}
        ></Column>
        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={openCategoriesDialog}
        style={{ width: '1200px' }}
        header={'Категории посещаемые пользователем : ' + logEntity?.userName}
        modal
        className="p-fluid"
        footer={categoriesDialogFooter}
        onHide={hideCategoriesDialog}
        dismissableMask={true}
      >
        <DataTable value={categoriesForUser as any[]} sortMode="multiple" className="oi-p-datatable">
          <Column headerStyle={{ width: '5rem' }} field="categoryName" sortable header="Наименование категории"></Column>
          <Column field="countClickTotal" sortable header="Количество нажатий"></Column>
          <Column field="countClickByCity" sortable header="Количество по городам"></Column>
          <Column field="countClickNotByCity" sortable header="Количество не по городам"></Column>
        </DataTable>
      </Dialog>

      <Dialog
        visible={openCitiesDialog}
        style={{ width: '600px' }}
        header={'Города посещаемые пользователем : ' + logEntity?.userName}
        modal
        className="p-fluid"
        footer={citiesDialogFooter}
        onHide={hideCitiesDialog}
        dismissableMask={true}
      >
        <DataTable value={citiesForUser as any[]} sortMode="multiple" className="oi-p-datatable">
          <Column headerStyle={{ width: '5rem' }} field="cityName" sortable header="Наименование города"></Column>
          <Column field="countClickTotal" sortable header="Количество нажатий"></Column>
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
  getAllTgUsersForStatistics,
  getStatisticCategoryByChatId,
  getStatisticCityByChatId,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllStatisticsTgUsers);
