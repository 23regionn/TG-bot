import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './config-table.reducer';
import { IConfigTable } from 'app/shared/model/config-table.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IConfigTableProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ConfigTable = (props: IConfigTableProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { configTableList, match, loading } = props;
  return (
    <div>
      <h2 id="config-table-heading" data-cy="ConfigTableHeading">
        Config Tables
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Config Table
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {configTableList && configTableList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date One</th>
                <th>Date Two</th>
                <th>Long One</th>
                <th>String One</th>
                <th>Boolean One</th>
                <th>Boolean Two</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {configTableList.map((configTable, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${configTable.id}`} color="link" size="sm">
                      {configTable.id}
                    </Button>
                  </td>
                  <td>{configTable.dateOne ? <TextFormat type="date" value={configTable.dateOne} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{configTable.dateTwo ? <TextFormat type="date" value={configTable.dateTwo} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{configTable.longOne}</td>
                  <td>{configTable.stringOne}</td>
                  <td>{configTable.booleanOne ? 'true' : 'false'}</td>
                  <td>{configTable.booleanTwo ? 'true' : 'false'}</td>
                  <td>{configTable.date1 ? <TextFormat type="date" value={configTable.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{configTable.date2 ? <TextFormat type="date" value={configTable.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{configTable.long1}</td>
                  <td>{configTable.string1}</td>
                  <td>{configTable.boolean1 ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${configTable.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${configTable.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${configTable.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Config Tables found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ configTable }: IRootState) => ({
  configTableList: configTable.entities,
  loading: configTable.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConfigTable);
