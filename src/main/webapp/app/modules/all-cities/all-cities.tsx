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

export interface IAllCitiesProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllCities = (props: IAllCitiesProps) => {
  const [cityState, setCityState] = useState(null);
  const [editCityDialog, setEditCityDialog] = useState(false);
  const [createCityDialog, setCreateCityDialog] = useState(false);
  const nameCityValue = useRef(null);
  const statusValue = useRef(null);
  const [dateCreateCityState, setDateCreateCityState] = useState(null);

  useEffect(() => {
    props.getEntities();
  }, []);

  const { cityList: cityList, match, loading } = props;

  const editCity = rowData => {
    setCityState(rowData);
    setDateCreateCityState(new Date(rowData?.dateCreateCity));
    setEditCityDialog(true);
  };

  const createCityFunc = () => {
    setCreateCityDialog(true);
  };

  const hideDialog = () => {
    setCityState(null);
    setDateCreateCityState(null);
    nameCityValue.current.value = null;
    statusValue.current.value = null;
    setEditCityDialog(false);
  };

  const hideCreateDialog = () => {
    setCityState(null);
    setDateCreateCityState(null);
    nameCityValue.current.value = null;
    statusValue.current.value = null;
    setCreateCityDialog(false);
  };

  const editCityButton = () => {
    const entity = {
      id: cityState.id,
      cityName: nameCityValue.current.value,
      status: statusValue.current.value,
      dateCreateCity: dateCreateCityState,
    };

    props.partialUpdateCity(entity);

    setCityState(null);
    setDateCreateCityState(null);
    nameCityValue.current.value = null;
    statusValue.current.value = null;
    setEditCityDialog(false);
  };

  const createCityButton = () => {
    const entity = {
      cityName: nameCityValue.current.value,
      status: statusValue.current.value,
      dateCreateCity: dateCreateCityState,
    };

    props.createEntity(entity);

    setCityState(null);
    setDateCreateCityState(null);
    nameCityValue.current.value = null;
    statusValue.current.value = null;
    setCreateCityDialog(false);
  };

  const editCityDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={editCityButton} />
    </React.Fragment>
  );

  const createCityDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideCreateDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={createCityButton} />
    </React.Fragment>
  );

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => editCity(rowData)} />
        <Button
          id="button_basic"
          className="p-button-rounded p-button-warning p-mr-2"
          label="Список категорий"
          onClick={() => pushToCategoriesByCity(rowData)}
        />
      </React.Fragment>
    );
  };

  const pushToCategoriesByCity = rowData => {
    props.history.push({
      // pathname: `all-categories/by-city-id/${rowData.id}`,
      pathname: `all-categories/rel/by-city-id/${rowData.id}`,
    });
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>Все города в боте</div>
        <div>
          <Button id="button_basic" label="Создать город в боте" onClick={createCityFunc} />
        </div>
      </div>

      <br />
      <DataTable value={cityList as any[]} className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="id" header="№"></Column>
        <Column field="cityName" filter filterPlaceholder="Поиск по названию" sortable header="Наименование города"></Column>
        <Column
          field="dateCreateCity"
          header="Дата создания в боте"
          sortable
          dataType="date"
          body={rowData => new Date(rowData.dateCreateCity).toLocaleDateString()}
        ></Column>
        <Column
          field="status"
          header="Статус"
          body={rowData => <InputTextarea value={rowData?.status} readOnly={true} rows={2} cols={20} />}
        ></Column>
        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={editCityDialog}
        style={{ width: '600px' }}
        header={'Город - ' + cityState?.cityName}
        modal
        className="p-fluid"
        footer={editCityDialogFooter}
        onHide={hideDialog}
        dismissableMask={true}
      >
        <div className="p-field">
          <div> Наименование города </div>
          <InputText defaultValue={cityState?.cityName || ''} placeholder={'Введите наименование города'} ref={nameCityValue} />
        </div>

        <br />

        <div className="p-field">
          <div>Дата создания в боте города</div>
          <Calendar id="basic" value={dateCreateCityState} onChange={e => setDateCreateCityState(e.value)} showIcon dateFormat="dd/mm/yy" />
        </div>

        <br />

        <div className="p-field">
          <div> Статус </div>
          <InputText defaultValue={cityState?.status || ''} placeholder={'Введите статус'} ref={statusValue} />
        </div>
      </Dialog>

      <Dialog
        visible={createCityDialog}
        style={{ width: '600px' }}
        header={'Создание города'}
        modal
        className="p-fluid"
        footer={createCityDialogFooter}
        onHide={hideCreateDialog}
        dismissableMask={true}
      >
        <div className="p-field">
          <div> Наименование города </div>
          <InputText defaultValue={cityState?.cityName || ''} placeholder={'Введите наименование города'} ref={nameCityValue} />
        </div>

        <br />

        <div className="p-field">
          <div>Дата создания в боте города</div>
          <Calendar id="basic" value={dateCreateCityState} onChange={e => setDateCreateCityState(e.value)} showIcon dateFormat="dd/mm/yy" />
        </div>

        <br />

        <div className="p-field">
          <div> Статус </div>
          <InputText defaultValue={''} placeholder={'Введите статус'} ref={statusValue} />
        </div>
      </Dialog>
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

export default connect(mapStateToProps, mapDispatchToProps)(AllCities);
