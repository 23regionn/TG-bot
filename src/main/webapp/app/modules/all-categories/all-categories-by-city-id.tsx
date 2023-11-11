import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { addCategoryToCity, getCategoriesByCityId } from './all-categories.reducer';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { getEntity as getCity } from 'app/entities/city/city.reducer';
import { Dropdown } from 'primereact/dropdown';
import { getOnlyCategories } from '../../entities/category/category.reducer';
import { NavLink } from 'reactstrap';

export interface IAllCategoriesByCityIdIAllCategoriesProps
  extends StateProps,
    DispatchProps,
    RouteComponentProps<{ url: string; id: string }> {}

export const AllCategoriesByCityId = (props: IAllCategoriesByCityIdIAllCategoriesProps) => {
  const [addCategoryDialog, setAddCategoryDialog] = useState(false);
  const [categoryToCity, setCategoryToCity] = useState<any>(null);

  useEffect(() => {
    props.getCategoriesByCityId(props.match.params.id);
    props.getCity(props.match.params.id);
  }, []);

  const { categoryList, match, loading, city, categories } = props;

  const addCategoryFunc = () => {
    getAllCategories();
    setAddCategoryDialog(true);
  };

  const hideAddDialog = () => {
    window.console.log(categoryToCity, 'categoryToCity');

    setCategoryToCity(null);
    setAddCategoryDialog(false);
  };

  const addCategoryButton = () => {
    window.console.log(categoryToCity, 'categoryToCity');

    const entity = {
      idCat: categoryToCity?.id,
      idCity: props.match.params.id,
    };

    props.addCategoryToCity(entity);

    setCategoryToCity(null);
    setAddCategoryDialog(false);
  };

  const addCategoryDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideAddDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={addCategoryButton} />
    </React.Fragment>
  );

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <NavLink
          tag={Link}
          to={`/all-channels/by-city-and-category/${props.match.params.id}/${rowData.id}`}
          className="d-flex align-items-center"
        >
          <Button id="button_basic" className="p-button-rounded p-button-warning p-mr-2" label="Список каналов по городу" />{' '}
        </NavLink>
      </React.Fragment>
    );
  };

  const addCategory = (e: { value: any }) => {
    setCategoryToCity(e.value);
  };

  const getAllCategories = () => {
    props.getOnlyCategories();
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>Все категории по городу {city.cityName}</div>
        <div>
          <Button id="button_basic" label="Добавить категорию" onClick={addCategoryFunc} />
        </div>
      </div>

      <br />
      <DataTable value={categoryList as any[]} className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="id" header="№"></Column>
        <Column field="name" filter filterPlaceholder="Поиск по наименованию" sortable header="Наименование категории"></Column>
        <Column
          field="isFirst"
          sortable
          header="isFirst"
          body={rowData => (rowData.isFirst !== null ? (rowData.isFirst ? 'Да' : 'Нет') : null)}
        ></Column>
        <Column
          field="isShow"
          sortable
          header="isShow"
          body={rowData => (rowData.isShow !== null ? (rowData.isShow ? 'Да' : 'Нет') : null)}
        ></Column>
        <Column
          field="score"
          sortable
          header="Порядковый номер"
          body={rowData => (rowData.score !== null ? rowData.score : 'null')}
        ></Column>
        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={addCategoryDialog}
        style={{ width: '600px' }}
        header={'Добавить категорию к городу - ' + city.cityName}
        modal
        className="p-fluid"
        footer={addCategoryDialogFooter}
        onHide={hideAddDialog}
        dismissableMask={true}
      >
        <div className="p-field">
          <div> Выбрать категорию категории </div>
          <Dropdown
            value={categoryToCity}
            options={categories as any}
            onChange={addCategory}
            optionLabel="name"
            placeholder="Выбрать категорию"
          />
        </div>
      </Dialog>
    </div>
  );
};

const mapStateToProps = ({ categoryList, city, category }: IRootState) => ({
  categories: category.entities,
  categoryList: categoryList.entities,
  loading: categoryList.loading,
  city: city.entity,
});

const mapDispatchToProps = {
  getCity,
  getCategoriesByCityId,
  getOnlyCategories,
  addCategoryToCity,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllCategoriesByCityId);
