import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './rel-category-city.reducer';
import { IRelCategoryCity } from 'app/shared/model/rel-category-city.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRelCategoryCityProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const RelCategoryCity = (props: IRelCategoryCityProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { relCategoryCityList, match, loading } = props;
  return (
    <div>
      <h2 id="rel-category-city-heading" data-cy="RelCategoryCityHeading">
        Rel Category Cities
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Rel Category City
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {relCategoryCityList && relCategoryCityList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Is Show</th>
                <th>Score</th>
                <th>Is First</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {relCategoryCityList.map((relCategoryCity, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${relCategoryCity.id}`} color="link" size="sm">
                      {relCategoryCity.id}
                    </Button>
                  </td>
                  <td>{relCategoryCity.isShow ? 'true' : 'false'}</td>
                  <td>{relCategoryCity.score}</td>
                  <td>{relCategoryCity.isFirst ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${relCategoryCity.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${relCategoryCity.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${relCategoryCity.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Rel Category Cities found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ relCategoryCity }: IRootState) => ({
  relCategoryCityList: relCategoryCity.entities,
  loading: relCategoryCity.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelCategoryCity);
