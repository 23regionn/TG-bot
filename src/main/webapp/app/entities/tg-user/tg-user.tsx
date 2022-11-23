import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tg-user.reducer';
import { ITGUser } from 'app/shared/model/tg-user.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITGUserProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TGUser = (props: ITGUserProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { tGUserList, match, loading } = props;
  return (
    <div>
      <h2 id="tg-user-heading" data-cy="TGUserHeading">
        TG Users
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new TG User
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tGUserList && tGUserList.length > 0 ? (
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
                <th>Balance</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tGUserList.map((tGUser, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${tGUser.id}`} color="link" size="sm">
                      {tGUser.id}
                    </Button>
                  </td>
                  <td>{tGUser.idTgUser}</td>
                  <td>{tGUser.firstName}</td>
                  <td>
                    {tGUser.registrationDate ? <TextFormat type="date" value={tGUser.registrationDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{tGUser.userRole}</td>
                  <td>{tGUser.isAdmin ? 'true' : 'false'}</td>
                  <td>{tGUser.score}</td>
                  <td>{tGUser.isBlocked ? 'true' : 'false'}</td>
                  <td>{tGUser.chatId}</td>
                  <td>{tGUser.isDelete ? 'true' : 'false'}</td>
                  <td>{tGUser.date1 ? <TextFormat type="date" value={tGUser.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tGUser.date2 ? <TextFormat type="date" value={tGUser.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tGUser.long1}</td>
                  <td>{tGUser.string1}</td>
                  <td>{tGUser.boolean1 ? 'true' : 'false'}</td>
                  <td>{tGUser.balance ? <Link to={`balance/${tGUser.balance.id}`}>{tGUser.balance.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tGUser.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tGUser.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tGUser.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No TG Users found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ tGUser }: IRootState) => ({
  tGUserList: tGUser.entities,
  loading: tGUser.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TGUser);
