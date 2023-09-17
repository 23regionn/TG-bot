import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getOnlyCategories } from './all-categories.reducer';
import { IMessegePannel } from 'app/shared/model/messege-pannel.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAllCategoriesProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AllCategoriesTest = (props: IAllCategoriesProps) => {
  useEffect(() => {
    props.getOnlyCategories();
  }, []);

  const handleSyncList = () => {
    props.getOnlyCategories();
  };

  const { categoriesList, match, loading } = props;
  return (
    <div>
      <h2 id="messege-pannel-heading" data-cy="MessegePannelHeading">
        Список всех категорий
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Обновить список
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Создать новую категорию
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {categoriesList && categoriesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>isFirst</th>
                <th>isShow</th>
                <th>score</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {categoriesList.map((category, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${category.id}`} color="link" size="sm">
                      {category.id}
                    </Button>
                  </td>
                  <td>{category.name}</td>
                  {category.isFirst != null ? <td>{category.isFirst ? 'да' : 'нет'}</td> : <td>{'null'}</td>}
                  {category.isShow != null ? <td>{category.isShow ? 'да' : 'нет'}</td> : <td>{'null'}</td>}
                  {category.score != null ? <td>{category.score}</td> : <td>{'null'}</td>}
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${category.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${category.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">Категории не найдены</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ categoryList }: IRootState) => ({
  categoriesList: categoryList.entities,
  loading: categoryList.loading,
});

const mapDispatchToProps = {
  getOnlyCategories,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllCategoriesTest);
