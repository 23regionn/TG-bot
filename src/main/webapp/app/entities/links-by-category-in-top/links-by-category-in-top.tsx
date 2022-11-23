import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './links-by-category-in-top.reducer';
import { ILinksByCategoryInTop } from 'app/shared/model/links-by-category-in-top.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILinksByCategoryInTopProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const LinksByCategoryInTop = (props: ILinksByCategoryInTopProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { linksByCategoryInTopList, match, loading } = props;
  return (
    <div>
      <h2 id="links-by-category-in-top-heading" data-cy="LinksByCategoryInTopHeading">
        Links By Category In Tops
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Links By Category In Top
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {linksByCategoryInTopList && linksByCategoryInTopList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Price Diapozon</th>
                <th>Link</th>
                <th>Chanell Admin Id</th>
                <th>Date Post Link Start</th>
                <th>Date Post Link End</th>
                <th>Position Between Links</th>
                <th>Show Link</th>
                <th>Is Delete</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Chanell</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {linksByCategoryInTopList.map((linksByCategoryInTop, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${linksByCategoryInTop.id}`} color="link" size="sm">
                      {linksByCategoryInTop.id}
                    </Button>
                  </td>
                  <td>{linksByCategoryInTop.category}</td>
                  <td>{linksByCategoryInTop.priceDiapozon}</td>
                  <td>{linksByCategoryInTop.link}</td>
                  <td>{linksByCategoryInTop.chanellAdminId}</td>
                  <td>
                    {linksByCategoryInTop.datePostLinkStart ? (
                      <TextFormat type="date" value={linksByCategoryInTop.datePostLinkStart} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {linksByCategoryInTop.datePostLinkEnd ? (
                      <TextFormat type="date" value={linksByCategoryInTop.datePostLinkEnd} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{linksByCategoryInTop.positionBetweenLinks}</td>
                  <td>{linksByCategoryInTop.showLink ? 'true' : 'false'}</td>
                  <td>{linksByCategoryInTop.isDelete ? 'true' : 'false'}</td>
                  <td>
                    {linksByCategoryInTop.date1 ? (
                      <TextFormat type="date" value={linksByCategoryInTop.date1} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {linksByCategoryInTop.date2 ? (
                      <TextFormat type="date" value={linksByCategoryInTop.date2} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{linksByCategoryInTop.long1}</td>
                  <td>{linksByCategoryInTop.string1}</td>
                  <td>{linksByCategoryInTop.boolean1 ? 'true' : 'false'}</td>
                  <td>
                    {linksByCategoryInTop.chanell ? (
                      <Link to={`chanell/${linksByCategoryInTop.chanell.id}`}>{linksByCategoryInTop.chanell.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${linksByCategoryInTop.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${linksByCategoryInTop.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${linksByCategoryInTop.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Links By Category In Tops found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ linksByCategoryInTop }: IRootState) => ({
  linksByCategoryInTopList: linksByCategoryInTop.entities,
  loading: linksByCategoryInTop.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LinksByCategoryInTop);
