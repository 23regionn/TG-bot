import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './links-by-category-in-top.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILinksByCategoryInTopDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LinksByCategoryInTopDetail = (props: ILinksByCategoryInTopDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { linksByCategoryInTopEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="linksByCategoryInTopDetailsHeading">LinksByCategoryInTop</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{linksByCategoryInTopEntity.id}</dd>
          <dt>
            <span id="category">Category</span>
            <UncontrolledTooltip target="category">категория</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopEntity.category}</dd>
          <dt>
            <span id="priceDiapozon">Price Diapozon</span>
            <UncontrolledTooltip target="priceDiapozon">ценовой диапозон</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopEntity.priceDiapozon}</dd>
          <dt>
            <span id="link">Link</span>
            <UncontrolledTooltip target="link">ссылку на канал</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopEntity.link}</dd>
          <dt>
            <span id="chanellAdminId">Chanell Admin Id</span>
            <UncontrolledTooltip target="chanellAdminId">ссылка на админа</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopEntity.chanellAdminId}</dd>
          <dt>
            <span id="datePostLinkStart">Date Post Link Start</span>
            <UncontrolledTooltip target="datePostLinkStart">дата размещения ссылки</UncontrolledTooltip>
          </dt>
          <dd>
            {linksByCategoryInTopEntity.datePostLinkStart ? (
              <TextFormat value={linksByCategoryInTopEntity.datePostLinkStart} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="datePostLinkEnd">Date Post Link End</span>
            <UncontrolledTooltip target="datePostLinkEnd">дата окончания размещения</UncontrolledTooltip>
          </dt>
          <dd>
            {linksByCategoryInTopEntity.datePostLinkEnd ? (
              <TextFormat value={linksByCategoryInTopEntity.datePostLinkEnd} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="positionBetweenLinks">Position Between Links</span>
            <UncontrolledTooltip target="positionBetweenLinks">место среди ссылок</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopEntity.positionBetweenLinks}</dd>
          <dt>
            <span id="showLink">Show Link</span>
          </dt>
          <dd>{linksByCategoryInTopEntity.showLink ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{linksByCategoryInTopEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>
            {linksByCategoryInTopEntity.date1 ? (
              <TextFormat value={linksByCategoryInTopEntity.date1} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>
            {linksByCategoryInTopEntity.date2 ? (
              <TextFormat value={linksByCategoryInTopEntity.date2} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{linksByCategoryInTopEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{linksByCategoryInTopEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{linksByCategoryInTopEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Chanell</dt>
          <dd>{linksByCategoryInTopEntity.chanell ? linksByCategoryInTopEntity.chanell.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/links-by-category-in-top" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/links-by-category-in-top/${linksByCategoryInTopEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ linksByCategoryInTop }: IRootState) => ({
  linksByCategoryInTopEntity: linksByCategoryInTop.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LinksByCategoryInTopDetail);
