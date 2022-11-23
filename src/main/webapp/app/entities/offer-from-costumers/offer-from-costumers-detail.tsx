import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './offer-from-costumers.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfferFromCostumersDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OfferFromCostumersDetail = (props: IOfferFromCostumersDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { offerFromCostumersEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="offerFromCostumersDetailsHeading">OfferFromCostumers</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{offerFromCostumersEntity.id}</dd>
          <dt>
            <span id="text">Text</span>
            <UncontrolledTooltip target="text">текст предложения, обратная связь</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersEntity.text}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="adminId">Admin Id</span>
            <UncontrolledTooltip target="adminId">ссылка на админа, который пропустил отзыв</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersEntity.adminId}</dd>
          <dt>
            <span id="isActive">Is Active</span>
            <UncontrolledTooltip target="isActive">предложение = обработали или нет</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>
            {offerFromCostumersEntity.date1 ? (
              <TextFormat value={offerFromCostumersEntity.date1} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>
            {offerFromCostumersEntity.date2 ? (
              <TextFormat value={offerFromCostumersEntity.date2} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{offerFromCostumersEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{offerFromCostumersEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{offerFromCostumersEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>T G User</dt>
          <dd>{offerFromCostumersEntity.tGUser ? offerFromCostumersEntity.tGUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/offer-from-costumers" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/offer-from-costumers/${offerFromCostumersEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ offerFromCostumers }: IRootState) => ({
  offerFromCostumersEntity: offerFromCostumers.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OfferFromCostumersDetail);
