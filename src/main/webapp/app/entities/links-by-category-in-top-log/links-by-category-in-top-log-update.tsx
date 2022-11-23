import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILinksByCategoryInTop } from 'app/shared/model/links-by-category-in-top.model';
import { getEntities as getLinksByCategoryInTops } from 'app/entities/links-by-category-in-top/links-by-category-in-top.reducer';
import { getEntity, updateEntity, createEntity, reset } from './links-by-category-in-top-log.reducer';
import { ILinksByCategoryInTopLog } from 'app/shared/model/links-by-category-in-top-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILinksByCategoryInTopLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LinksByCategoryInTopLogUpdate = (props: ILinksByCategoryInTopLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { linksByCategoryInTopLogEntity, linksByCategoryInTops, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/links-by-category-in-top-log');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getLinksByCategoryInTops();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.datePostLinkStart = convertDateTimeToServer(values.datePostLinkStart);
    values.datePostLinkEnd = convertDateTimeToServer(values.datePostLinkEnd);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...linksByCategoryInTopLogEntity,
        ...values,
        linksByCategoryInTop: linksByCategoryInTops.find(it => it.id.toString() === values.linksByCategoryInTopId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gidApp.linksByCategoryInTopLog.home.createOrEditLabel" data-cy="LinksByCategoryInTopLogCreateUpdateHeading">
            Create or edit a LinksByCategoryInTopLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : linksByCategoryInTopLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="links-by-category-in-top-log-id">ID</Label>
                  <AvInput id="links-by-category-in-top-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="categoryLabel" for="links-by-category-in-top-log-category">
                  Category
                </Label>
                <AvField id="links-by-category-in-top-log-category" data-cy="category" type="text" name="category" />
                <UncontrolledTooltip target="categoryLabel">категория</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="priceDiapozonLabel" for="links-by-category-in-top-log-priceDiapozon">
                  Price Diapozon
                </Label>
                <AvField
                  id="links-by-category-in-top-log-priceDiapozon"
                  data-cy="priceDiapozon"
                  type="string"
                  className="form-control"
                  name="priceDiapozon"
                />
                <UncontrolledTooltip target="priceDiapozonLabel">ценовой диапозон</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="links-by-category-in-top-log-link">
                  Link
                </Label>
                <AvField id="links-by-category-in-top-log-link" data-cy="link" type="text" name="link" />
                <UncontrolledTooltip target="linkLabel">ссылку на канал</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="chanellAdminIdLabel" for="links-by-category-in-top-log-chanellAdminId">
                  Chanell Admin Id
                </Label>
                <AvField
                  id="links-by-category-in-top-log-chanellAdminId"
                  data-cy="chanellAdminId"
                  type="string"
                  className="form-control"
                  name="chanellAdminId"
                />
                <UncontrolledTooltip target="chanellAdminIdLabel">ссылка на админа</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="datePostLinkStartLabel" for="links-by-category-in-top-log-datePostLinkStart">
                  Date Post Link Start
                </Label>
                <AvInput
                  id="links-by-category-in-top-log-datePostLinkStart"
                  data-cy="datePostLinkStart"
                  type="datetime-local"
                  className="form-control"
                  name="datePostLinkStart"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={
                    isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopLogEntity.datePostLinkStart)
                  }
                />
                <UncontrolledTooltip target="datePostLinkStartLabel">дата размещения ссылки</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="datePostLinkEndLabel" for="links-by-category-in-top-log-datePostLinkEnd">
                  Date Post Link End
                </Label>
                <AvInput
                  id="links-by-category-in-top-log-datePostLinkEnd"
                  data-cy="datePostLinkEnd"
                  type="datetime-local"
                  className="form-control"
                  name="datePostLinkEnd"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopLogEntity.datePostLinkEnd)}
                />
                <UncontrolledTooltip target="datePostLinkEndLabel">дата окончания размещения</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="positionBetweenLinksLabel" for="links-by-category-in-top-log-positionBetweenLinks">
                  Position Between Links
                </Label>
                <AvField
                  id="links-by-category-in-top-log-positionBetweenLinks"
                  data-cy="positionBetweenLinks"
                  type="string"
                  className="form-control"
                  name="positionBetweenLinks"
                />
                <UncontrolledTooltip target="positionBetweenLinksLabel">место среди ссылок</UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="showLinkLabel">
                  <AvInput
                    id="links-by-category-in-top-log-showLink"
                    data-cy="showLink"
                    type="checkbox"
                    className="form-check-input"
                    name="showLink"
                  />
                  Show Link
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput
                    id="links-by-category-in-top-log-isDelete"
                    data-cy="isDelete"
                    type="checkbox"
                    className="form-check-input"
                    name="isDelete"
                  />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="links-by-category-in-top-log-date1">
                  Date 1
                </Label>
                <AvInput
                  id="links-by-category-in-top-log-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopLogEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="links-by-category-in-top-log-date2">
                  Date 2
                </Label>
                <AvInput
                  id="links-by-category-in-top-log-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.linksByCategoryInTopLogEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="links-by-category-in-top-log-long1">
                  Long 1
                </Label>
                <AvField id="links-by-category-in-top-log-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="links-by-category-in-top-log-string1">
                  String 1
                </Label>
                <AvField id="links-by-category-in-top-log-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput
                    id="links-by-category-in-top-log-boolean1"
                    data-cy="boolean1"
                    type="checkbox"
                    className="form-check-input"
                    name="boolean1"
                  />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="links-by-category-in-top-log-linksByCategoryInTop">Links By Category In Top</Label>
                <AvInput
                  id="links-by-category-in-top-log-linksByCategoryInTop"
                  data-cy="linksByCategoryInTop"
                  type="select"
                  className="form-control"
                  name="linksByCategoryInTopId"
                >
                  <option value="" key="0" />
                  {linksByCategoryInTops
                    ? linksByCategoryInTops.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/links-by-category-in-top-log" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  linksByCategoryInTops: storeState.linksByCategoryInTop.entities,
  linksByCategoryInTopLogEntity: storeState.linksByCategoryInTopLog.entity,
  loading: storeState.linksByCategoryInTopLog.loading,
  updating: storeState.linksByCategoryInTopLog.updating,
  updateSuccess: storeState.linksByCategoryInTopLog.updateSuccess,
});

const mapDispatchToProps = {
  getLinksByCategoryInTops,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LinksByCategoryInTopLogUpdate);
