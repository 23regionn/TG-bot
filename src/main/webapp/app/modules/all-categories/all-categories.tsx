import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { createCategory, getOnlyCategories, partialUpdateCategory } from './all-categories.reducer';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { RadioButton } from 'primereact/radiobutton';
import { SelectButton } from 'primereact/selectbutton';
import { TriStateCheckbox } from 'primereact/tristatecheckbox';

// Primereact
// Primereact
// Primereact
export interface IAllCategoriesProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllCategories = (props: IAllCategoriesProps) => {
  const [category, setCategory] = useState(null);
  const [editCategoryDialog, setEditCategoryDialog] = useState(false);
  const [createCategoryDialog, setCreateCategoryDialog] = useState(false);
  const nameCategoryValue = useRef(null);
  const scoreValue = useRef(null);
  const [isFirstState, setIsFirstState] = useState(false);
  const [isShowState, setIsShowState] = useState(false);

  useEffect(() => {
    props.getOnlyCategories();
  }, []);

  const handleSyncList = () => {
    props.getOnlyCategories();
  };

  const { categoryList, match, loading } = props;

  const editCategory = rowData => {
    window.console.log(rowData, 'rowData222');

    setCategory(rowData);
    setIsFirstState(rowData?.isFirst);
    setIsShowState(rowData?.isShow);
    setEditCategoryDialog(true);
  };

  const createCategoryFunc = () => {
    setCreateCategoryDialog(true);
  };

  const hideDialog = () => {
    setCategory(null);
    nameCategoryValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowState(false);
    setIsFirstState(false);
    setEditCategoryDialog(false);
  };

  const hideCreateDialog = () => {
    nameCategoryValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowState(false);
    setIsFirstState(false);
    setCreateCategoryDialog(false);
  };

  const editCategoryButton = () => {
    const entity = {
      id: category.id,
      name: nameCategoryValue.current.value,
      isShow: isShowState,
      isFirst: isFirstState,
      score: scoreValue.current.value,
    };

    props.partialUpdateCategory(entity);

    nameCategoryValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowState(false);
    setIsFirstState(false);
    setCategory(null);
    setEditCategoryDialog(false);
  };

  const createCategoryButton = () => {
    const entity = {
      name: nameCategoryValue.current.value,
      isShow: isShowState,
      isFirst: isFirstState,
      score: scoreValue.current.value,
    };

    props.createCategory(entity);

    nameCategoryValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowState(false);
    setIsFirstState(false);
    setCreateCategoryDialog(false);
  };

  const editCategoryDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={editCategoryButton} />
    </React.Fragment>
  );

  const createCategoryDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideCreateDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={createCategoryButton} />
    </React.Fragment>
  );

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => editCategory(rowData)} />
        <Button
          id="button_basic"
          className="p-button-rounded p-button-warning p-mr-2"
          label="Список каналов"
          onClick={() => pushToChannels(rowData)}
        />
      </React.Fragment>
    );
  };

  /*const pushToChannels = rowData => {
    props.history.push({
      pathname: `all-channels/by-category-id/${rowData.id}`,
    });
  };*/

  const pushToChannels = rowData => {
    props.history.push({
      pathname: `all-channels/rel/by-category-id/${rowData.id}`,
    });
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>Все категории</div>
        <div>
          <Button id="button_basic" label="Создать категорию" onClick={createCategoryFunc} />
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
          body={rowData => (rowData.isFirst !== null ? (rowData.isFirst ? 'Да' : 'Нет') : 'null')}
        ></Column>
        <Column
          field="isShow"
          sortable
          header="isShow"
          body={rowData => (rowData.isShow !== null ? (rowData.isShow ? 'Да' : 'Нет') : 'null')}
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
        visible={editCategoryDialog}
        style={{ width: '600px' }}
        header={'Категория - ' + category?.name}
        modal
        className="p-fluid"
        footer={editCategoryDialogFooter}
        onHide={hideDialog}
        dismissableMask={true}
      >
        <div className="p-field">
          <div> Наименование категории </div>
          <InputText defaultValue={category?.name || ''} placeholder={'Введите название категории'} ref={nameCategoryValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Порядковый номер </div>
          <InputText defaultValue={category?.score || ''} placeholder={'Введите порядковый номер'} ref={scoreValue} />
        </div>

        <br />

        <div className="card">
          Отображать первым - isFirst ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isFirstState} onChange={e => setIsFirstState(e.value)} />
            <div>{String(isFirstState)}</div>
          </div>
        </div>

        <br />

        <div className="card">
          Отображать - isShow ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isShowState} onChange={e => setIsShowState(e.value)} />
            <div>{String(isShowState)}</div>
          </div>
        </div>

        <br />
      </Dialog>

      <Dialog
        visible={createCategoryDialog}
        style={{ width: '600px' }}
        header={'Создание категории'}
        modal
        className="p-fluid"
        footer={createCategoryDialogFooter}
        onHide={hideCreateDialog}
        dismissableMask={true}
      >
        <div className="p-field">
          <div> Наименование категории </div>
          <InputText
            // defaultValue={category?.name || ''}
            placeholder={'Введите название категории'}
            ref={nameCategoryValue}
          />
        </div>

        <br />

        <div className="p-field">
          <div> Порядковый номер </div>
          <InputText
            // defaultValue={category?.score || ''}
            placeholder={'Введите порядковый номер'}
            ref={scoreValue}
          />
        </div>

        <br />

        <div className="card">
          Отображать первым - isFirst ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isFirstState} onChange={e => setIsFirstState(e.value)} />
            <div>{String(isFirstState)}</div>
          </div>
        </div>

        <br />

        <div className="card">
          Отображать - isShow ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isShowState} onChange={e => setIsShowState(e.value)} />
            <div>{String(isShowState)}</div>
          </div>
        </div>

        <br />
      </Dialog>
    </div>
  );
};

const mapStateToProps = ({ categoryList }: IRootState) => ({
  categoryList: categoryList.entities,
  loading: categoryList.loading,
});

const mapDispatchToProps = {
  getOnlyCategories,
  partialUpdateCategory,
  createCategory,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllCategories);
