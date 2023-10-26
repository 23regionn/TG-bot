import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './count-channel-click-page-log.reducer';
import { ICountChannelClickPageLog } from 'app/shared/model/count-channel-click-page-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICountChannelClickPageLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CountChannelClickPageLog = (props: ICountChannelClickPageLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { countChannelClickPageLogList, match, loading } = props;
  return (
    <div>
      <h2 id="count-channel-click-page-log-heading" data-cy="CountChannelClickPageLogHeading">
        Count Channel Click Page Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Count Channel Click Page Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {countChannelClickPageLogList && countChannelClickPageLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Chat Id</th>
                <th>Date Log</th>
                <th>Id Channel</th>
                <th>Page Number</th>
                <th>Id Category</th>
                <th>Id City</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {countChannelClickPageLogList.map((countChannelClickPageLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${countChannelClickPageLog.id}`} color="link" size="sm">
                      {countChannelClickPageLog.id}
                    </Button>
                  </td>
                  <td>{countChannelClickPageLog.chatId}</td>
                  <td>
                    {countChannelClickPageLog.dateLog ? (
                      <TextFormat type="date" value={countChannelClickPageLog.dateLog} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{countChannelClickPageLog.idChannel}</td>
                  <td>{countChannelClickPageLog.pageNumber}</td>
                  <td>{countChannelClickPageLog.idCategory}</td>
                  <td>{countChannelClickPageLog.idCity}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${countChannelClickPageLog.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${countChannelClickPageLog.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${countChannelClickPageLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Count Channel Click Page Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ countChannelClickPageLog }: IRootState) => ({
  countChannelClickPageLogList: countChannelClickPageLog.entities,
  loading: countChannelClickPageLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CountChannelClickPageLog);
