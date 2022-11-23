import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tg-user-log.reducer';
import { ITGUserLog } from 'app/shared/model/tg-user-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITGUserLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TGUserLog = (props: ITGUserLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { tGUserLogList, match, loading } = props;
  return (
    <div>
      <h2 id="tg-user-log-heading" data-cy="TGUserLogHeading">
        TG User Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new TG User Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tGUserLogList && tGUserLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Id Tg User</th>
                <th>First Name</th>
                <th>Registration Date</th>
                <th>User Role</th>
                <th>Is Admin</th>
                <th>Score</th>
                <th>Is Blocked</th>
                <th>Chat Id</th>
                <th>Is Delete</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>T G User</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tGUserLogList.map((tGUserLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${tGUserLog.id}`} color="link" size="sm">
                      {tGUserLog.id}
                    </Button>
                  </td>
                  <td>{tGUserLog.idTgUser}</td>
                  <td>{tGUserLog.firstName}</td>
                  <td>
                    {tGUserLog.registrationDate ? (
                      <TextFormat type="date" value={tGUserLog.registrationDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{tGUserLog.userRole}</td>
                  <td>{tGUserLog.isAdmin ? 'true' : 'false'}</td>
                  <td>{tGUserLog.score}</td>
                  <td>{tGUserLog.isBlocked ? 'true' : 'false'}</td>
                  <td>{tGUserLog.chatId}</td>
                  <td>{tGUserLog.isDelete ? 'true' : 'false'}</td>
                  <td>{tGUserLog.date1 ? <TextFormat type="date" value={tGUserLog.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tGUserLog.date2 ? <TextFormat type="date" value={tGUserLog.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tGUserLog.long1}</td>
                  <td>{tGUserLog.string1}</td>
                  <td>{tGUserLog.boolean1 ? 'true' : 'false'}</td>
                  <td>{tGUserLog.tGUser ? <Link to={`tg-user/${tGUserLog.tGUser.id}`}>{tGUserLog.tGUser.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tGUserLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tGUserLog.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tGUserLog.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No TG User Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ tGUserLog }: IRootState) => ({
  tGUserLogList: tGUserLog.entities,
  loading: tGUserLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TGUserLog);
