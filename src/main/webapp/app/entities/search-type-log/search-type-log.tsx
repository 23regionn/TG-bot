import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './search-type-log.reducer';
import { ISearchTypeLog } from 'app/shared/model/search-type-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISearchTypeLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SearchTypeLog = (props: ISearchTypeLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { searchTypeLogList, match, loading } = props;
  return (
    <div>
      <h2 id="search-type-log-heading" data-cy="SearchTypeLogHeading">
        Search Type Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Search Type Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {searchTypeLogList && searchTypeLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Chat Id</th>
                <th>Date Log</th>
                <th>Inline Search</th>
                <th>Page Search</th>
                <th>Page Number</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {searchTypeLogList.map((searchTypeLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${searchTypeLog.id}`} color="link" size="sm">
                      {searchTypeLog.id}
                    </Button>
                  </td>
                  <td>{searchTypeLog.chatId}</td>
                  <td>
                    {searchTypeLog.dateLog ? <TextFormat type="date" value={searchTypeLog.dateLog} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{searchTypeLog.inlineSearch ? 'true' : 'false'}</td>
                  <td>{searchTypeLog.pageSearch ? 'true' : 'false'}</td>
                  <td>{searchTypeLog.pageNumber}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${searchTypeLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${searchTypeLog.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${searchTypeLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Search Type Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ searchTypeLog }: IRootState) => ({
  searchTypeLogList: searchTypeLog.entities,
  loading: searchTypeLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SearchTypeLog);
