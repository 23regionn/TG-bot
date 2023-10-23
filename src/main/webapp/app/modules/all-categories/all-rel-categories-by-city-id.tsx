import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { getEntity as getCity } from 'app/entities/city/city.reducer';
import { Dropdown } from 'primereact/dropdown';
import { getOnlyCategories } from '../../entities/category/category.reducer';
import { NavLink } from 'reactstrap';
import {
  createRelCityCategory,
  getEntitiesByCityId as getCategoriesByCity,
  partialUpdateRelCityCategory,
} from 'app/entities/rel-category-city/rel-category-city.reducer';
import { InputText } from 'primereact/inputtext';
import { TriStateCheckbox } from 'primereact/tristatecheckbox';
import { InputTextarea } from 'primereact/inputtextarea';

export interface IRelAllCategoriesByCityIdIAllCategoriesProps
  extends StateProps,
    DispatchProps,
    RouteComponentProps<{ url: string; id: string }> {}

export const AllRelCategoriesByCityId = (props: IRelAllCategoriesByCityIdIAllCategoriesProps) => {
  const [addCategoryDialog, setAddCategoryDialog] = useState(false);
  const [categoryToCity, setCategoryToCity] = useState<any>(null);
  const [rel, setRel] = useState(null);
  const [isShowRel, setIsShowRel] = useState(false);
  const [isFirstRel, setIsFirstRel] = useState(false);
  const [isEditRelDialog, setEditRelDialog] = useState(false);
  const commentValue = useRef(null);
  const scoreValue = useRef(null);

  useEffect(() => {
    // props.getCategoriesByCityId(props.match.params.id);
    props.getCity(props.match.params.id);
    props.getCategoriesByCity(props.match.params.id);
  }, []);

  const { categoryList, match, loading, city, categories, categoriesCity } = props;

  const addCategoryFunc = () => {
    getAllCategories();
    setAddCategoryDialog(true);
  };

  const hideAddDialog = () => {
    window.console.log(categoryToCity, 'categoryToCity');

    setCategoryToCity(null);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowRel(false);
    setIsFirstRel(false);
    setRel(null);
    setAddCategoryDialog(false);
  };

  const addCategoryButton = () => {
    window.console.log(categoryToCity, 'categoryToCity');

    const entity = {
      idCat: categoryToCity?.id,
      idCity: props.match.params.id,
      score: scoreValue.current.value,
      isShow: isShowRel,
      isFirst: isFirstRel,
      comment: commentValue.current.value,
    };

    props.createRelCityCategory(entity);

    setCategoryToCity(null);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowRel(false);
    setIsFirstRel(false);
    setRel(null);
    setAddCategoryDialog(false);
  };

  const addCategoryDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideAddDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={addCategoryButton} />
    </React.Fragment>
  );

  const editRel = rowData => {
    window.console.log(rowData, 'rowData-rowData');
    setRel(rowData);
    setIsShowRel(rowData?.isShow);
    setIsFirstRel(rowData?.isFirst);
    setEditRelDialog(true);
  };

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => editRel(rowData)} />
        <NavLink
          tag={Link}
          // to={`/all-channels/by-city-and-category/${props.match.params.id}/${rowData.id}`}
          to={`/all-channels/rel/channels-city-category/${rowData?.id}`}
          className="d-flex align-items-center"
        >
          <Button id="button_basic" className="p-button-rounded p-button-warning p-mr-2" label="Список каналов по городу" />{' '}
        </NavLink>
      </React.Fragment>
    );
  };

  const hideDialog = () => {
    setEditRelDialog(false);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowRel(false);
    setIsFirstRel(false);
    setRel(null);
  };

  const editRelButton = () => {
    const entity = {
      id: rel.id,
      score: scoreValue.current.value,
      isShow: isShowRel,
      isFirst: isFirstRel,
      comment: commentValue.current.value,
      idCity: props.match.params.id,
    };

    props.partialUpdateRelCityCategory(entity);

    setEditRelDialog(false);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowRel(false);
    setIsFirstRel(false);
    setRel(null);
  };

  const editRelDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={editRelButton} />
    </React.Fragment>
  );

  /*const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => editChannel(rowData)} />
      </React.Fragment>
    );
  };*/

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
      <DataTable value={categoriesCity as any[]} className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="id" header="№"></Column>
        <Column field="category.name" filter filterPlaceholder="Поиск на наименованию" sortable header="Наименование категории"></Column>
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
        <Column style={{ maxWidth: '15vw' }} field="comment" header="Комментарий" body={rowData => rowData?.comment}></Column>
        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={isEditRelDialog}
        style={{ width: '600px' }}
        header={'Категория - ' + rel?.category?.name}
        modal
        className="p-fluid"
        footer={editRelDialogFooter}
        onHide={hideDialog}
        dismissableMask={true}
      >
        <br />

        <div className="p-field">
          <div> Порядковый номер </div>
          <InputText defaultValue={rel?.score || ''} placeholder={'Введите порядковый номер'} ref={scoreValue} />
        </div>

        <br />

        <div className="card">
          Отображать - isShow ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isShowRel} onChange={e => setIsShowRel(e.value)} />
            <div>{String(isShowRel)}</div>
          </div>
        </div>

        <br />

        <div className="card">
          Первая - isFirst ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isFirstRel} onChange={e => setIsFirstRel(e.value)} />
            <div>{String(isFirstRel)}</div>
          </div>
        </div>

        <br />

        <div className="p-field">
          <div> Комментарий </div>
          <InputTextarea defaultValue={rel?.comment} rows={2} cols={20} ref={commentValue} />
        </div>

        <br />
      </Dialog>

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

          <br />

          <div className="p-field">
            <div> Порядковый номер </div>
            <InputText defaultValue={rel?.score || ''} placeholder={'Введите порядковый номер'} ref={scoreValue} />
          </div>

          <br />

          <div className="card">
            Отображать - isShow ?
            <div className="p-field-checkbox p-m-0">
              <TriStateCheckbox value={isShowRel} onChange={e => setIsShowRel(e.value)} />
              <div>{String(isShowRel)}</div>
            </div>
          </div>

          <br />

          <div className="card">
            Первая - isFirst ?
            <div className="p-field-checkbox p-m-0">
              <TriStateCheckbox value={isFirstRel} onChange={e => setIsFirstRel(e.value)} />
              <div>{String(isFirstRel)}</div>
            </div>
          </div>

          <br />

          <div className="p-field">
            <div> Комментарий </div>
            <InputTextarea defaultValue={rel?.comment} rows={2} cols={20} ref={commentValue} />
          </div>

          <br />
        </div>
      </Dialog>
    </div>
  );
};

const mapStateToProps = ({ categoryList, city, category, relCategoryCity }: IRootState) => ({
  categories: category.entities,
  categoryList: categoryList.entities,
  loading: categoryList.loading,
  city: city.entity,
  categoriesCity: relCategoryCity.entities,
});

const mapDispatchToProps = {
  getCity,
  getOnlyCategories,
  getCategoriesByCity,
  partialUpdateRelCityCategory,
  createRelCityCategory,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllRelCategoriesByCityId);
