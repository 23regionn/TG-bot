import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './review.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReviewDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ReviewDetail = (props: IReviewDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { reviewEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="reviewDetailsHeading">Review</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{reviewEntity.id}</dd>
          <dt>
            <span id="isActive">Is Active</span>
            <UncontrolledTooltip target="isActive">отзыв активен или не активен = прошел или не прошел</UncontrolledTooltip>
          </dt>
          <dd>{reviewEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="text">Text</span>
            <UncontrolledTooltip target="text">текст отзыва</UncontrolledTooltip>
          </dt>
          <dd>{reviewEntity.text}</dd>
          <dt>
            <span id="userId">User Id</span>
            <UncontrolledTooltip target="userId">айди пользователя который написал</UncontrolledTooltip>
          </dt>
          <dd>{reviewEntity.userId}</dd>
          <dt>
            <span id="linkToSaller">Link To Saller</span>
            <UncontrolledTooltip target="linkToSaller">ссылка на канал продавца рекламы от пользователя</UncontrolledTooltip>
          </dt>
          <dd>{reviewEntity.linkToSaller}</dd>
          <dt>
            <span id="adminId">Admin Id</span>
            <UncontrolledTooltip target="adminId">ссылка на админа, который пропустил отзыв</UncontrolledTooltip>
          </dt>
          <dd>{reviewEntity.adminId}</dd>
          <dt>
            <span id="isNegative">Is Negative</span>
            <UncontrolledTooltip target="isNegative">отзыв положительный или негативный</UncontrolledTooltip>
          </dt>
          <dd>{reviewEntity.isNegative ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{reviewEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{reviewEntity.date1 ? <TextFormat value={reviewEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{reviewEntity.date2 ? <TextFormat value={reviewEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{reviewEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{reviewEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{reviewEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>T G User</dt>
          <dd>{reviewEntity.tGUser ? reviewEntity.tGUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/review" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/review/${reviewEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ review }: IRootState) => ({
  reviewEntity: review.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ReviewDetail);
