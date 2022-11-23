import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './links-by-category-in-top-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILinksByCategoryInTopLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LinksByCategoryInTopLogDetail = (props: ILinksByCategoryInTopLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { linksByCategoryInTopLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="linksByCategoryInTopLogDetailsHeading">LinksByCategoryInTopLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.id}</dd>
          <dt>
            <span id="category">Category</span>
            <UncontrolledTooltip target="category">категория</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.category}</dd>
          <dt>
            <span id="priceDiapozon">Price Diapozon</span>
            <UncontrolledTooltip target="priceDiapozon">ценовой диапозон</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.priceDiapozon}</dd>
          <dt>
            <span id="link">Link</span>
            <UncontrolledTooltip target="link">ссылку на канал</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.link}</dd>
          <dt>
            <span id="chanellAdminId">Chanell Admin Id</span>
            <UncontrolledTooltip target="chanellAdminId">ссылка на админа</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.chanellAdminId}</dd>
          <dt>
            <span id="datePostLinkStart">Date Post Link Start</span>
            <UncontrolledTooltip target="datePostLinkStart">дата размещения ссылки</UncontrolledTooltip>
          </dt>
          <dd>
            {linksByCategoryInTopLogEntity.datePostLinkStart ? (
              <TextFormat value={linksByCategoryInTopLogEntity.datePostLinkStart} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="datePostLinkEnd">Date Post Link End</span>
            <UncontrolledTooltip target="datePostLinkEnd">дата окончания размещения</UncontrolledTooltip>
          </dt>
          <dd>
            {linksByCategoryInTopLogEntity.datePostLinkEnd ? (
              <TextFormat value={linksByCategoryInTopLogEntity.datePostLinkEnd} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="positionBetweenLinks">Position Between Links</span>
            <UncontrolledTooltip target="positionBetweenLinks">место среди ссылок</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.positionBetweenLinks}</dd>
          <dt>
            <span id="showLink">Show Link</span>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.showLink ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>
            {linksByCategoryInTopLogEntity.date1 ? (
              <TextFormat value={linksByCategoryInTopLogEntity.date1} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>
            {linksByCategoryInTopLogEntity.date2 ? (
              <TextFormat value={linksByCategoryInTopLogEntity.date2} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{linksByCategoryInTopLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Links By Category In Top</dt>
          <dd>{linksByCategoryInTopLogEntity.linksByCategoryInTop ? linksByCategoryInTopLogEntity.linksByCategoryInTop.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/links-by-category-in-top-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/links-by-category-in-top-log/${linksByCategoryInTopLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ linksByCategoryInTopLog }: IRootState) => ({
  linksByCategoryInTopLogEntity: linksByCategoryInTopLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LinksByCategoryInTopLogDetail);
