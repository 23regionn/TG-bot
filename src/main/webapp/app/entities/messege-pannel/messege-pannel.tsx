import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './messege-pannel.reducer';
import { IMessegePannel } from 'app/shared/model/messege-pannel.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMessegePannelProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MessegePannel = (props: IMessegePannelProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { messegePannelList, match, loading } = props;
  return (
    <div>
      <h2 id="messege-pannel-heading" data-cy="MessegePannelHeading">
        Messege Pannels
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Messege Pannel
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {messegePannelList && messegePannelList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Id Message</th>
                <th>Id Channel</th>
                <th>Date Create Message</th>
                <th>Text Message</th>
                <th>Id Admin</th>
                <th>Comment</th>
                <th>Status</th>
                <th>Service Field 1</th>
                <th>Service Field 2</th>
                <th>Service Field 3</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {messegePannelList.map((messegePannel, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${messegePannel.id}`} color="link" size="sm">
                      {messegePannel.id}
                    </Button>
                  </td>
                  <td>{messegePannel.idMessage}</td>
                  <td>{messegePannel.idChannel}</td>
                  <td>
                    {messegePannel.dateCreateMessage ? (
                      <TextFormat type="date" value={messegePannel.dateCreateMessage} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{messegePannel.textMessage}</td>
                  <td>{messegePannel.idAdmin}</td>
                  <td>{messegePannel.comment}</td>
                  <td>{messegePannel.status}</td>
                  <td>{messegePannel.serviceField1}</td>
                  <td>{messegePannel.serviceField2}</td>
                  <td>{messegePannel.serviceField3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${messegePannel.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${messegePannel.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${messegePannel.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Messege Pannels found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ messegePannel }: IRootState) => ({
  messegePannelList: messegePannel.entities,
  loading: messegePannel.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MessegePannel);
