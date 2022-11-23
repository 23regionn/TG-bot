import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './review.reducer';
import { IReview } from 'app/shared/model/review.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReviewProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Review = (props: IReviewProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { reviewList, match, loading } = props;
  return (
    <div>
      <h2 id="review-heading" data-cy="ReviewHeading">
        Reviews
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Review
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {reviewList && reviewList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Is Active</th>
                <th>Text</th>
                <th>User Id</th>
                <th>Link To Saller</th>
                <th>Admin Id</th>
                <th>Is Negative</th>
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
              {reviewList.map((review, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${review.id}`} color="link" size="sm">
                      {review.id}
                    </Button>
                  </td>
                  <td>{review.isActive ? 'true' : 'false'}</td>
                  <td>{review.text}</td>
                  <td>{review.userId}</td>
                  <td>{review.linkToSaller}</td>
                  <td>{review.adminId}</td>
                  <td>{review.isNegative ? 'true' : 'false'}</td>
                  <td>{review.isDelete ? 'true' : 'false'}</td>
                  <td>{review.date1 ? <TextFormat type="date" value={review.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{review.date2 ? <TextFormat type="date" value={review.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{review.long1}</td>
                  <td>{review.string1}</td>
                  <td>{review.boolean1 ? 'true' : 'false'}</td>
                  <td>{review.tGUser ? <Link to={`tg-user/${review.tGUser.id}`}>{review.tGUser.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${review.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${review.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${review.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Reviews found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ review }: IRootState) => ({
  reviewList: review.entities,
  loading: review.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Review);
